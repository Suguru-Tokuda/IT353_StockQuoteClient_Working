/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.StockModel;

/**
 *
 * @author vyass
 */
@ManagedBean
@SessionScoped
public class StockController implements Serializable {

    private String response;

    @ManagedProperty("#{stockModel}")
    private StockModel stockModel;

    /**
     * @return the stockModel
     */
    public StockModel getStockModel() {
        if (stockModel == null) {
            stockModel = new StockModel();
        }
        return stockModel;
    }

    /**
     * @param stockModel the stockModel to set
     */
    public void setStockModel(StockModel stockModel) {
        this.stockModel = stockModel;
    }

    public String updateQuoteAJAX() {
        String retVal = null;
        try {
            System.out.println("retVal before: " + retVal);
            retVal = getDowJonesStock(getStockModel().getStockSym());
            System.out.println("retVal after: " + retVal);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
            retVal = "";
        }

        return retVal;
    }

    public void stockPoll() {
        try {
            if (!stockModel.getStockSym().isEmpty()) {
                getStockModel().setStockQuote(getDowJonesStock(getStockModel().getStockSym()));
                getStockModel().setTime(LocalDateTime.now().toString());
            }
        } catch (NullPointerException n) {
            System.out.println(n);
        }
    }

    public String retrieveQuote() {
        String navi = null;
        getStockModel().setStockQuote(getDowJonesStock(getStockModel().getStockSym()));
        System.out.println("Server worked");
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        nav.performNavigation("response?faces-redirect=true");
        return navi;
    }

    public String getBackToIndex() {
        String navi = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        nav.performNavigation("index?faces-redirect=true");
        return navi;
    }

    private static String getDowJonesStock(java.lang.String stockSymbol) {
        String retVal = null;
        sq.StockQuote_Service service = new sq.StockQuote_Service();
        sq.StockQuote port = service.getStockQuotePort();
        String quote = null;
        try {
            quote = port.getDowJonesStock(stockSymbol);
        } catch (Exception ne) {
            System.out.println("Here is a null pointer exception: " + ne);
            quote = "";
        }

        retVal = quote;
        return retVal;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        String retVal = null;
        try {
            retVal = updateQuoteAJAX();
        } catch (NullPointerException e) {
            retVal = "";
        }
        return retVal;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

}