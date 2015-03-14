package com.khs.stockticker;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by jwb on 3/13/15.
 */
public interface WebSocketMessageHandler {
   public String handleMessage(ChannelHandlerContext ctx, String frameText);
}
