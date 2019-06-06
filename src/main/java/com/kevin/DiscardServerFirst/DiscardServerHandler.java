package com.kevin.DiscardServerFirst;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        System.out.println(channel.id() + " 注册成功!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("进来一次");
        ByteBuf buf = (ByteBuf)msg;
//        int length = buf.readInt();
//        String kevin = buf.readBytes(length).toString(CharsetUtil.UTF_8);
//        System.out.println("length:" + length + ",kevin:" + kevin);
        String kevin = buf.toString(CharsetUtil.UTF_8);
        System.out.println("kevin:|" + kevin);
        ByteBuf out = ctx.alloc().buffer(9);
        out.writeInt(5);
        out.writeBytes(kevin.getBytes());
        ctx.writeAndFlush(out);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}