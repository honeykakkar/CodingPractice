package SystemDesigning;

/*
 * Author: Honey Kakkar
 * Created on: January 05, 2017
 * Package: SystemDesigning
 * Project: Coding Practice in JAVA
 */

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*
Give a stream of stock prices, design a data structure to support the following operations:

StockSticker(int n) Initialize the size of the ticker.
void addOrUpdate(String stock, double price) add or update the price of a stock to the data structure.
top() return the top k price stocks and their current prices.
 */

public class StockTicker {

    class Stock implements Comparable<Stock> {

        final String name;
        final double price;

        Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public int compareTo(Stock o) {
            double diff = o.price - this.price;
            if (diff > 0)
                return 1;
            else if (diff < 0)
                return -1;
            return this.name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return String.format("[Stock: %s, Price: %.3f]", this.name, this.price);
        }
    }

    private final HashMap<String, Stock> stockStore;
    private final TreeSet<Stock> topStocks;

    private StockTicker(int n) {
        stockStore = new HashMap<>(n);
        topStocks = new TreeSet<>();
    }

    private void addOrUpdate(String stock, double price) {
        Stock newStock = new Stock(stock, price);
        if (stockStore.containsKey(stock)) {
            Stock prevStock = stockStore.get(stock);
            topStocks.remove(prevStock);
        }
        stockStore.put(stock, newStock);
        topStocks.add(newStock);
    }

    private List<Stock> getTopK(int K) {
        List<Stock> top = new ArrayList<>(K);
        Iterator<Stock> iterator = topStocks.iterator();
        while (K > 0 && iterator.hasNext()) {
            top.add(iterator.next());
            --K;
        }
        return top;
    }

    public static void main(String[] args) {
        StockTicker stockTicker = new StockTicker(10);
        ThreadLocalRandom TLR = ThreadLocalRandom.current();
        stockTicker.addOrUpdate("A", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("B", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("C", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("D", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("E", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("F", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("G", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("H", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("I", TLR.nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("J", TLR.nextDouble(1.0, 10.0));
        List<Stock> top = stockTicker.getTopK(5);
        System.out.println(top);
        stockTicker.addOrUpdate("D", ThreadLocalRandom.current().nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("E", ThreadLocalRandom.current().nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("F", ThreadLocalRandom.current().nextDouble(1.0, 10.0));
        stockTicker.addOrUpdate("A", ThreadLocalRandom.current().nextDouble(1.0, 10.0));
        top = stockTicker.getTopK(5);
        System.out.println(top);
    }
}