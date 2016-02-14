package core;

import entity.Unit;
import graph.Graph;
import graph.GraphNode;
import sceneElements.SpriteImage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hung on 13/02/16.
 */
public class UnitSpawner {

    private ArrayList<Unit> unitPool;
    private int unitPoolCount = 0;
    private int totalSpawnables = 10;
    private int spawnCount = 0;
    private int spawnlimit;
    private GameRunTime runTime;
    Random rndSearchGen;

    private String[] names;
    private String[] descriptions;

    public UnitSpawner(GameRunTime runTime)
    {
        names = new String[] {"Banshee", "Demon", "Death knight"};
        descriptions = new String[] {"Depth First Search", "Breadth First Search", "A* Search", "Selection Sort", "Insertion Sort", "Bubble Sort"};
        rndSearchGen = new Random(System.currentTimeMillis());
        unitPool = new ArrayList<>();

        this.runTime = runTime;

        Graph graph = runTime.getEngine().getGraph();
        Renderer renderer = runTime.getRenderer();
        // this should be passed in
        GraphNode goal = graph.getNodes().get(graph.getNodes().size() - 1);

        for (unitPoolCount = 0; unitPoolCount < totalSpawnables; unitPoolCount++)
        {
            CreateUnit(graph, renderer, goal);
        }
    }

    private Unit CreateUnit(Graph graph, Renderer renderer, GraphNode goal) {

        SpriteImage sprite = new SpriteImage("http://imgur.com/FAt5VBo.png", null);
        sprite.setOnMouseClicked(e ->
        {
            sprite.requestFocus();
        });

        // doing random for now, could return sequence of numbers representing units wanted
        int index = rndSearchGen.nextInt(3);

        Unit unit = new Unit(unitPoolCount, names[index], graph.nodeWith(new GraphNode(0, 10)), sprite, Unit.Search.values()[index], Unit.Sort.values()[index], graph, goal ,renderer);
        sprite.setEntity(unit);
        unit.setCurrentPixel(sprite.getX(),sprite.getY());
        unitPool.add(unit);

        return unit;
    }

    private void spawnUnit()
    {
        Unit newUnit;

        if (unitPool.size() > 0)
        {
            newUnit = unitPool.remove(0);
        }
        else
        {
            newUnit = CreateUnit(runTime.getEngine().getGraph(),runTime.getRenderer(), runTime.getEngine().getGraph().getNodes().get(31));
        }

        spawnCount++;

        runTime.getEngine().getEntities().add(newUnit);
        runTime.getRenderer().drawEntity(newUnit);
    }

    private void despawnUnit(Unit unit)
    {
        unitPool.add(unit);
        //remove from list here?
    }

    public void update() {
        if (spawnCount < spawnlimit)
        {
            System.out.println("spawining, Spawn limit " + spawnlimit);
            spawnUnit();
        }
    }

    public void setSpawnlimit(int spawnlimit) {
        this.spawnlimit = spawnlimit;
    }
}
