package org.rplbo.app.ug8;

public class InventoryItem {
    private String itemName;
    private int acquired;
    private int used;
    private int totalStock;

    public InventoryItem(String itemName, int acquired, int used, int totalStock) {
        this.itemName = itemName;
        this.acquired = acquired;
        this.used = used;
        this.totalStock = totalStock;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAcquired() {
        return acquired;
    }

    public void setAcquired(int acquired) {
        this.acquired = acquired;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }
}