/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Suguru
 */
public class StockModel implements Serializable {
    
    private String stockSym;
    private String stockQuote;
    private String time;
    
    /**
     * @return the stockSym
     */
    public String getStockSym() {
        return stockSym;
    }

    /**
     * @param stockSym the stockSym to set
     */
    public void setStockSym(String stockSym) {
        this.stockSym = stockSym;
    }

    /**
     * @return the stockQuote
     */
    public String getStockQuote() {
        return stockQuote;
    }

    /**
     * @param stockQuote the stockQuote to set
     */
    public void setStockQuote(String stockQuote) {
        this.stockQuote = stockQuote;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
}
