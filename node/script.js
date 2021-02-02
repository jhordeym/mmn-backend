const https = require('https');

process.env['NODE_TLS_REJECT_UNAUTHORIZED'] = 0;

const username = 'travinedGatewayAdmin';
const password = 'trav2020inedAdmin!#1234';

const options1 = {
    host: 'backend.travined.com',
    port: 8080,
    method: 'GET',
    path: '/api/accounts/accounts',
    // authentication headers
    headers: {
        'Authorization': 'Basic ' + Buffer.from(username + ':' + password).toString('base64')
    }
};

const options2 = {
    host: 'api.saveonresorts.com',
    path: '/v2/clubmembership/updatemembers',
    method: 'POST',
    // authentication headers
    headers: {
        'Content-Type': 'application/json',
        'x-saveon-username': 'myTrip53121',
        'x-saveon-secret': 'o2m9je7tdjfrxrys',
    }
}

function http1() {
    https.get(options1, (resp) => {
        let body = '';

        // A chunk of data has been recieved.
        resp.on('data', (data) => {
            body += data;
        });

        // The whole response has been received. Print out the result.
        resp.on('end', () => {
            const list = JSON.parse(body);
            const nextPayload = list.map(e => {
                console.log(e.email, e.inviteToken);
                return {
                    "Requests": [
                        {
                            "Email": e.email,
                            "Updates": {
                                //"OtherId": e.inviteToken
                                "ContractNumber": e.id
                            }
                        }
                    ]
                };

            });

            nextPayload.forEach(e => {
                http2(JSON.stringify(e));
            });

            // console.log(nextPayload);
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });
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
            console.log(JSON.parse(body));
        });

    }).on("error", (err) => {
        console.log("Error: " + err.message);
    });

    postReq.write(payload);
    postReq.end();
}

http1();



