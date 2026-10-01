package com.tech.ayugram.play.stub;

import java.util.List;

public final class IndoorBuilding {
    private String id;
    private List<IndoorLevel> levels;
    private int defaultLevelIndex;
    private boolean underground;

    public IndoorBuilding(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<IndoorLevel> getLevels() {
        return levels;
    }

    public int getDefaultLevelIndex() {
        return defaultLevelIndex;
    }

    public boolean isUnderground() {
        return underground;
    }

    public IndoorLevel getActiveLevel() {
        return levels != null && !levels.isEmpty() && defaultLevelIndex >= 0 && defaultLevelIndex < levels.size()
            ? levels.get(defaultLevelIndex) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndoorBuilding that = (IndoorBuilding) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}