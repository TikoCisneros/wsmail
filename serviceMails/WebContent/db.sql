CREATE TABLE ws_mail_yachay
(
  mai_id character varying(20) NOT NULL,
  mai_server character varying(100),
  mai_usuario character varying(100),
  mai_password character varying(200),
  mail_pwd_resp character varying(200),
  mai_estado character(1),
  CONSTRAINT ws_mail_yachay_pkey PRIMARY KEY (mai_id)
);