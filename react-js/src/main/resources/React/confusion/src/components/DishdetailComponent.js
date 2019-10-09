import React from 'react';
import {Card, CardImg, CardText, CardBody, CardTitle, Breadcrumb, BreadcrumbItem,
Navbar, NavbarBrand, Nav, NavbarToggler, NavItem,Button, Modal, ModalHeader, ModalBody,Form,
FormGroup, Label,Input} from 'reactstrap';

import {Link } from 'react-router-dom';
import {Control, LocalForm, Errors} from 'react-redux-form';
import CommentForm from './CommentFormComponent';

    const DishDetail = (props)=>{
        console.log('Dishdetail  component did render is invoked');

        const dish = props.dish;
        if(dish != null){
        return (
        <div className="container">
            <div className="row">
                <Breadcrumb>
                    <BreadcrumbItem><Link to='/menu'>Menu</Link></BreadcrumbItem>
                    <BreadcrumbItem active>{props.dish.name}</BreadcrumbItem>
                </Breadcrumb>
                <div className="col-12">
                    <h3>{props.dish.name}</h3>
                    <hr />
                </div>
            </div>

            <div className="row">
                        <RenderDish dish = {props.dish}/>

                        <RenderComments comments={props.comments} />
                        <CommentForm />
                </div>
            </div>

        );
        }
        else {
            return (
            <div></div>
            );
        }


    }

    function RenderDish({dish}){
        return(
        <div className="col-12 col-md-5 m-1">
        <Card>
           <CardImg top  src={dish.image} alt= {dish.name}/>
           <CardBody>
                <CardTitle>{dish.name}</CardTitle>
                <CardText>{dish.description}</CardText>
           </CardBody>
        </Card>
        </div>

        );

    }
   function RenderComments({comments}){
    if(comments == null || comments.length < 1){
        return(
            <div></div>
        );
    }

    const commts = comments.map((post) =>
                               <div key={post.id} className="m-1">
                                   <li >{post.comment} </li>
                                   <li >--{post.author},  {new Intl.DateTimeFormat('en-US',{year:'numeric', month:'short',day:'2-digit'}).format(new Date(Date.parse(post.date)))} </li>
                               </div>

                           );

    return(
        <div className="col-12 col-md-5 m-1">
            <h4>Comments</h4>
            <ul className="list-unstyled">
                {commts}
            </ul>
             <Nav className="ml-auto" navbar>
                <NavItem>
                    <Button outline  ><span className="fa fa-pencil fa-lg"></span> Submit Comment</Button>
                </NavItem>
            </Nav>
        </div>
    );

    }


export default DishDetail;