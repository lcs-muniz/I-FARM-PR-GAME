package com.fazenda.model;

public class GameSession {
    private int currentFarmerId;
    private int currentRanchoId;

    public int getCurrentFarmerId() {
        return currentFarmerId;
    }
    public void setCurrentFarmerId(int currentFarmerId) {
        this.currentFarmerId = currentFarmerId;
    }
    public int getCurrentRanchoId() {
        return currentRanchoId;
    }
    public void setCurrentRanchoId(int currentRanchoId) {
        this.currentRanchoId = currentRanchoId;
    }
}
