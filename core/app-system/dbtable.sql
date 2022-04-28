create table sys_menus
(
    id        int auto_increment
        primary key,
    menu_id   varchar(36)   not null comment '菜单ID',
    pid       varchar(36)   null comment '父菜单ID',
    `key`     varchar(255)  not null comment '菜单标识',
    title     varchar(50)   not null comment '菜单名称',
    icon      varchar(255)  null comment '菜单图标',
    component varchar(255)  null comment '组件路径-前端一致',
    sort      int default 0 not null comment '排序',
    status    int default 1 not null comment '状态控制',
    remarks   varchar(255)  null comment '备注'
);

create table sys_org
(
    id          int auto_increment
        primary key,
    org_id      varchar(64)  not null comment '机构id',
    pid         varchar(64)  null comment '父项ID',
    name        varchar(255) not null comment '名称',
    remarks     varchar(255) null comment '备注',
    creator     varchar(255) null comment '创建人',
    create_time datetime     null comment '创建人',
    updater     varchar(255) null comment '修改人',
    update_time datetime     null comment '修改人'
)
    comment '机构';

create table sys_role_menus
(
    id          int auto_increment
        primary key,
    role_id     varchar(36)   not null comment '角色id',
    menu_id     varchar(36)   not null comment '菜单ID',
    status      int default 1 not null comment '状态控制',
    sort        int default 0 not null comment '菜单排序',
    creator     varchar(50)   null comment '创建人',
    create_time datetime      null comment '创建时间',
    remarks     varchar(255)  null comment '备注'
)
    comment '角色菜单';

create table sys_roles
(
    id      int auto_increment
        primary key,
    role_id varchar(36)   not null comment '角色ID',
    name    varchar(50)   null comment '角色名称',
    status  int default 1 not null comment '状态',
    remarks varchar(200)  null comment '备注'
);

create table sys_user
(
    id          int auto_increment
        primary key,
    uid         varchar(36)   not null comment '用户ID-登录ID',
    uname       varchar(64)   not null comment '用户名称',
    pwd         varchar(256)  not null comment '用户密码',
    company     varchar(100)  null comment '公司',
    department  json          null comment '部门',
    job         varchar(100)  null comment '工作职位',
    sno         int           null comment '工号',
    id_num      varchar(18)   null comment '身份证号码',
    email       varchar(100)  null comment '邮箱',
    phone       varchar(20)   null comment '电话号码',
    avatar      varchar(200)  null comment '头像',
    sign        varchar(200)  null comment '签名',
    reg_date    datetime      null comment '注册时间',
    status      int default 1 not null comment '人员状态',
    creator     varchar(50)   null comment '创建人',
    create_time datetime      null comment '创建时间',
    updater     varchar(50)   null comment '修改人',
    update_time datetime      null comment '修改时间',
    remark      varchar(1000) null comment '备注',
    constraint sys_user_uid_uindex
        unique (uid)
)
    comment '用户表';

create table sys_user_menus
(
    id          int auto_increment
        primary key,
    uid         varchar(36)   not null comment '用户id',
    menu_id     varchar(36)   not null comment '菜单ID',
    status      int default 1 not null comment '状态控制',
    creator     varchar(50)   null comment '创建人',
    create_time datetime      null comment '创建时间',
    remarks     varchar(255)  null comment '备注'
)
    comment '用户额外的菜单权限';

create table sys_user_role
(
    id          int auto_increment
        primary key,
    uid         varchar(36)   not null comment '用户id',
    role_id     varchar(36)   not null comment '角色ID',
    status      int default 1 not null comment '状态控制',
    creator     varchar(50)   null comment '创建人',
    create_time datetime      null comment '创建时间',
    remarks     varchar(255)  null comment '备注'
)
    comment '用户角色(可能一个用户多个角色)';
