/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDateTime;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vyass
 */
@ManagedBean
@SessionScoped
public class Controller {
    @ManagedProperty("#{stockSym}")
    private String stockSym;
    @ManagedProperty("#{stockQuote}")
    private String stockQuote;
    @ManagedProperty("#{time}")
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

    public void stockPoll() {
        
        try {
            if (!stockSym.isEmpty()) {
                stockQuote = getDowJonesStock(stockSym);
                this.setTime(LocalDateTime.now().toString());
            }
        } catch (NullPointerException n) {
            System.out.println(n);
        }
    }

    
    private static String getDowJonesStock(java.lang.String stockSymbol) {
        sq.StockQuote_Service service = new sq.StockQuote_Service();
        sq.StockQuote port = service.getStockQuotePort();
        return port.getDowJonesStock(stockSymbol);
    }
    
}
