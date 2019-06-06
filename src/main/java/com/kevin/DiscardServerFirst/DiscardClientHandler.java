package com.kevin.DiscardServerFirst;

import com.kevin.DiscardServerFirst.vo.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class DiscardClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = ctx.alloc().buffer(9);
        buf.writeInt(5);
        buf.writeBytes("kevin".getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("客户端读到了");
        ByteBuf buf = (ByteBuf)msg;
        String kevin = buf.toString(CharsetUtil.UTF_8);
        System.out.println("客户端读到:|" + kevin);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}