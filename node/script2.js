const https = require('https');

process.env['NODE_TLS_REJECT_UNAUTHORIZED'] = 0;

const username = 'travinedGatewayAdmin';
const password = 'trav2020inedAdmin!#1234';

const options1 = {
    host: 'backend.travined.com',
    port: 8080,
    method: 'POST',
    path: '/api/reservation/sor/members',
    // authentication headers
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + Buffer.from(username + ':' + password).toString('base64')
    }
};

const options2 = {
    host: 'api.saveonresorts.com',
    method: 'POST',
    path: '/clubmembership/getlogintokennovalidation',
    // authentication headers
    headers: {
        'Content-Type': 'application/json',
        'x-saveon-username': 'Passport53421',
        'x-saveon-secret': 'phorbizkp2foku82',
    }
};

const loginURL = 'https://members.travined.com/vacationclub/logincheck.aspx?Token=';
const redirectURL = '&RedirectURL=%2Fcars%2F';
const options3 = {
    host: 'backend.travined.com',
    port: 8080,
    method: 'POST',
    path: '/api/mail/send',
    // authentication headers
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + Buffer.from(username + ':' + password).toString('base64')
    }
}

function http1() {
    const postReq1 = https.request(options1, (resp) => {
        let body = '';

        // A chunk of data has been recieved.
        resp.on('data', (data) => {
            body += data;
        });

        // The whole response has been received. Print out the result.
        resp.on('end', () => {
            const list = JSON.parse(body);
            const nextPayload = list.map(e => {
                console.log(e.Email);
                return {
                    "Email": e.Email
                };

            });

            nextPayload.forEach(e => {
                http2(JSON.stringify(e));
            });
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });


    postReq1.write(JSON.stringify([]));
    postReq1.end();
}

function http2(payload) {
    const postReq = https.request(options2, (resp) => {
        let body = '';

        // A chunk of data has been recieved.
        resp.on('data', (data) => {
            body += data;
        });

        // The whole response has been received. Print out the result.
        resp.on('end', () => {
            // console.log(body);

            let token;
            try {
                token = JSON.parse(body).replace("\"", "").substr('LoginToken:'.length);
                if (token.length == 36) {
                    console.log(token);

                    const email = JSON.parse(payload).Email;
                    console.log(email)

                    const _email = {
                        "to": email,
                        "subject": "Welcome to Travined!",
                        "text": "Hello! </br>" +
                            "In order to use your passport please click the link bellow: </br>" +
                            loginURL + token + redirectURL
                    }
                    http3(JSON.stringify(_email));

                }
            } catch (e) {
                console.log('error ', e);
            }
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });

    postReq.write(payload);
    postReq.end();
}

function http3(payload) {
    const postReq = https.request(options3, (resp) => {
        let body = '';

        // A chunk of data has been recieved.
        resp.on('data', (data) => {
            body += data;
        });

        // The whole response has been received. Print out the result.
        resp.on('end', () => {
            console.log(body);
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });

    postReq.write(payload);
    postReq.end();
}

http1();

/*
http3(JSON.stringify(
    {
        "to": 'jhordeym@gmail.com',
        "subject": "Welcome to Travined!",
        "text": "<h1>Hello!</h1> </br>" +
            "In order to use your passport please click the link bellow: </br>" +
            loginURL + '8dab9717-8abc-4b9b-adba-d8b3eaf8cdea' + redirectURL
    })
)
*/


