FROM node:8.10.0
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY dist/movie-cruiser-frontend /usr/src/app/dist/movie-cruiser-frontend
RUN npm install http-server -g
CMD ["http-server", "dist/movie-cruiser-frontend"]
