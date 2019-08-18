var functions = require('firebase-functions');
var admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

//Listens change from firebase database
exports.sendNotification = functions.database.ref('/events/{pushId}')
     .onWrite((change, context) => {
         if (change.before.exists()) {
              return null;
         }
         // Grab the current value of what was written to the Realtime Database.
         var eventSnapshot = change.after.val();

         var topic = "android";

//        目前是向全部用户send notification。
//        因为这个是后台没有进行优化，优化的点是将user section化。
//        只对于相关地区或者固定范围的user进行send notification。

         var payload = {
             data: {
                 id : eventSnapshot.id,
                 type : eventSnapshot.event_type,
                 description : eventSnapshot.event_description
             }
         };

     // Send firebase cloud message to devices subscribed to the provided topic.
     return admin.messaging().sendToTopic(topic, payload)
         .then(function (response) {
             // See the MessagingTopicResponse reference documentation for the
             // contents of response.
             console.log("Successfully sent message:", response);
             return -1;
         })
         .catch(function (error) {
             console.log("Error sending message:", error);
         });
     })

