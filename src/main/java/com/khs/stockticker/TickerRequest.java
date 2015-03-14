package com.khs.stockticker;

/**
 * Created by jwb on 3/13/15.
 */
public class TickerRequest {
   private String command;
   private String tickerSymbol;

   public String getCommand() {
      return command;
   }

   public String getTickerSymbol() {
      return tickerSymbol;
   }

   public void setCommand(String command) {
      this.command = command;
   }

   public void setTickerSymbol(String tickerSymbol) {
      this.tickerSymbol = tickerSymbol;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) { return true; }
      if (o == null || getClass() != o.getClass()) { return false; }

      TickerRequest that = (TickerRequest) o;

      if (command != null ? !command.equals(that.command) : that.command != null) { return false; }
      if (tickerSymbol != null ? !tickerSymbol.equals(that.tickerSymbol) : that.tickerSymbol != null) { return false; }

      return true;
   }

   @Override
   public int hashCode() {
      int result = command != null ? command.hashCode() : 0;
      result = 31 * result + (tickerSymbol != null ? tickerSymbol.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "TickerRequest{"  +
              "command='"      + command + '\'' +
            ", tickerSymbol='" + tickerSymbol + '\'' +
            '}';
   }
}
