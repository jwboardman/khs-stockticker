package com.khs.stockticker;

/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server that accept the path of a file an echo back its content.
 */
public final class StockTickerServer {
   private static final Logger logger = LoggerFactory.getLogger(StockTickerServerHandler.class);
   private static final boolean SSL = System.getProperty("ssl") != null;
   private static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));

   public static void main(String[] args) throws Exception {
      // Configure SSL.
      final SslContext sslCtx;
      if (SSL) {
         SelfSignedCertificate ssc = new SelfSignedCertificate();
         sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
      } else {
         sslCtx = null;
      }

      // Configure the server.
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
         ServerBootstrap b = new ServerBootstrap();
         b.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 100)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new ChannelInitializer<SocketChannel>() {
             @Override
             public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                if (sslCtx != null) {
                   p.addLast(sslCtx.newHandler(ch.alloc()));
                }

                p.addLast("encoder", new HttpResponseEncoder());
                p.addLast("decoder", new HttpRequestDecoder());
                p.addLast("aggregator", new HttpObjectAggregator(65536));
                p.addLast("handler", new StockTickerServerHandler());
             }
          });

         // Start the server.
         ChannelFuture f = b.bind(PORT).sync();
         logger.info("Ticket Symbol Server started");

         // Wait until the server socket is closed.
         f.channel().closeFuture().sync();
      } finally {
         logger.info("Ticket Symbol Server shutdown started");
         // Shut down all event loops to terminate all threads.
         bossGroup.shutdownGracefully();
         workerGroup.shutdownGracefully();
         logger.info("Ticket Symbol Server shutdown completed");
      }
   }
}