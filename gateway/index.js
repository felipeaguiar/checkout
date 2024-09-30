const amqp = require('amqplib');
const express = require('express');
const app = express();

const RABBITMQ_HOST = process.env.RABBITMQ_HOST || 'localhost';

async function start() {
  const connection = await amqp.connect(`amqp://${RABBITMQ_HOST}`);
  const channel = await connection.createChannel();

  await channel.assertQueue('payments');
  await channel.assertQueue('payment_response');

  channel.consume('payments', async (msg) => {
    console.log('Processing payment request...');
    
    const orderId = msg.content.toString();
    console.log(orderId);

    const approved = Math.random() > 0.5;
    const status = approved ? 'APPROVED' : 'FAILED';

    const response = {
      orderId: parseInt(orderId),
      status: status
    };

    channel.sendToQueue('payment_response', Buffer.from(JSON.stringify(response)));

    channel.ack(msg);
  });

  app.listen(3000, () => {
    console.log('Payment Gateway running on port 3000');
  });
}

start();

