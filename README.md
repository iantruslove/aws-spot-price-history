# aws-spot-price-history

Simple Ring-based web service publishing AWS EC2 spot price records

Example query:
`curl -XGET 'http://localhost:3000/?limit=10&offset=10&startDate=2013-01-01&sortField=timestamp'`

## Heroku deployment

Easy.  Given a heroku remote called `heroku`, just

    git push heroku master

## Usage

Lein2 is a prerequisite.

Run locally with `lein ring server`

## License

Copyright © 2013 Ian Truslove

Distributed under the MIT License.
