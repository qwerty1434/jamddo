FROM node:16.16
RUN git clone -b frontend --single-branch https://github.com/qwerty1434/jamddo.git
WORKDIR jamddo/frontend/jamddo


RUN npm install 
RUN npm run lint -- --fix

EXPOSE 8081

CMD ["npm", "run", "serve"]