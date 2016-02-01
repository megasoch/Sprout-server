package com.sprout.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by megasoch on 11.11.2015.
 */
public class GameLogic implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameLogic gameLogic = (GameLogic) o;

        if (width != gameLogic.width) return false;
        if (height != gameLogic.height) return false;
        if (ACTIVE != gameLogic.ACTIVE) return false;
        if (size != gameLogic.size) return false;
        if (Float.compare(gameLogic.INDENT, INDENT) != 0) return false;
        if (Float.compare(gameLogic.RADIUS, RADIUS) != 0) return false;
        if (points != null ? !points.equals(gameLogic.points) : gameLogic.points != null) return false;
        return !(edges != null ? !edges.equals(gameLogic.edges) : gameLogic.edges != null);

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + (ACTIVE ? 1 : 0);
        result = 31 * result + size;
        result = 31 * result + (INDENT != +0.0f ? Float.floatToIntBits(INDENT) : 0);
        result = 31 * result + (RADIUS != +0.0f ? Float.floatToIntBits(RADIUS) : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }

    private static final long serialVersionUID = 7863262235394607247L;
    private int width;
    private int height;

    @Override
    public String toString() {
        return "com.sprout.game.GameLogic{" +
                "width=" + width +
                ", height=" + height +
                ", ACTIVE=" + ACTIVE +
                ", size=" + size +
                ", INDENT=" + INDENT +
                ", RADIUS=" + RADIUS +
                ", points=" + points +
                ", edges=" + edges +
                '}';
    }

    public void setACTIVE(boolean ACTIVE) {

        this.ACTIVE = ACTIVE;
    }

    public boolean isACTIVE() {

        return ACTIVE;
    }

    private boolean ACTIVE;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPoints(Set<PointElement> points) {
        this.points = points;
    }

    public void setEdges(Set<EdgeElement> edges) {
        this.edges = edges;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public float getINDENT() {
        return INDENT;
    }

    private int size;
    private final float INDENT = 50;
    private final float RADIUS = 10;
    private Set<PointElement> points = new HashSet<PointElement>(0);
    private Set<EdgeElement> edges = new HashSet<EdgeElement>(0);

    public void setNetworkParams(int size) {
        this.size = size;
    }

    public void initStartingPoints(int numberOfStartingPoints) {
        while (points.size() != numberOfStartingPoints) {
            float x = INDENT + (float)Math.random() * (width - 2 * INDENT);
            float y = INDENT + (float)Math.random() * (height - 2 * INDENT);
            PointElement p = new PointElement(x, y, false);
            points.add(p);
        }
    }

    public void addPoint(float x, float y) {
        points.add(new PointElement(x, y, false));
    }

    public void addPoint(PointElement p) {
        points.add(p);
    }

    public Set<EdgeElement> getEdges() {
        return edges;
    }

    public void addEdge(ArrayList<PointElement> a) {
        ArrayList<PointElement> p = new ArrayList<PointElement>(a);
        edges.add(new EdgeElement(p));
    }

    public float getRADIUS() {
        return RADIUS;
    }

    public Set<PointElement> getPoints() {
        return points;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }
}
