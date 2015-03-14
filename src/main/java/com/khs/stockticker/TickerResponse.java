package com.khs.stockticker;

import java.util.Map;

/**
 * Created by jwb on 3/13/15.
 */
public class TickerResponse {
   private String result;
   private Map<String,String> tickerData;

   public String getResult() {
      return result;
   }

   public Map<String,String> getTickerData() {
      return tickerData;
   }

   public void setResult(String command) {
      this.result = command;
   }

   public void setTickerData(Map<String,String> tickerData) {
      this.tickerData = tickerData;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) { return true; }
      if (o == null || getClass() != o.getClass()) { return false; }

      TickerResponse that = (TickerResponse) o;

      if (result != null ? !result.equals(that.result) : that.result != null) { return false; }
      if (tickerData != null ? !tickerData.equals(that.tickerData) : that.tickerData != null) { return false; }

      return true;
   }

   @Override
   public int hashCode() {
      int result = this.result != null ? this.result.hashCode() : 0;
      result = 31 * result + (tickerData != null ? tickerData.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "TickerResponse{"  +
              "result='"      + result + '\'' +
            ", tickerData='" + tickerData + '\'' +
            '}';
   }
}
