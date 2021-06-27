import React, { Component } from 'react';
import { connect } from 'dva';
import { Layout, Space, Card, Table , Select, Form, Input, Button} from 'antd';

const { Header, Footer, Sider, Content } = Layout;
const { Option } = Select;


@connect(({ productInfo, loading }) => ({
  productInfo,
  loading: loading.effects['productInfo/fetchData'],
}))
class Products extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id : 0,
      quantity : 0,
      products : [{id : 0, quantity : 0}]
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'productInfo/fetchInitialData',
    });

    dispatch({
      type: 'productInfo/fetchData',
    });

  }

  handleProductNameChange = idx => evt => {
    const newProducts = this.state.products.map((product, sidx) => {
      if (idx !== sidx) return product;
      return { ...product, id: evt.target.value };
    });

    this.setState({ products: newProducts });
  };

  render() {
    const {
      loading
    } = this.props;
    const { productList, price ,productOrderList} = this.props.productInfo;

    const columns = [
      {
        title: 'Product Name',
        dataIndex: 'productName',
        key: 'productName',
      },
      {
        title: 'Carton Capacity',
        dataIndex: 'cartonCapacity',
        key: 'cartonCapacity',
      },
      {
        title: 'Per Carton Price',
        dataIndex: 'cartonPrice',
        key: 'cartonPrice',
      },
    ];

    const rangeColumns = [
      {
        title: 'Product Name',
        dataIndex: 'productName',
        key: 'productName',
      },
      {
        title: 'Unit Price',
        dataIndex: 'unitPrice',
        key: 'unitPrice',
      },
      {
        title: 'Order Quantity',
        dataIndex: 'orderedQuantity',
        key: 'orderedQuantity',
      },
    ];
    const onFinish = (values) => {
      const { dispatch } = this.props;
      dispatch({
        type: 'productInfo/calculatePrice',
        payload : { 'requestedProduct' : [values]  }
      });

    };


    return (
     <div>
       <Layout >
         <Sider style={{ width: '100%', minHeight: 1500}}>Sider</Sider>
         <Layout >
           <Header>Header</Header>
           <Content >
             <Card title="Product Lists" style={{ width: '100%', height : '25%' }}>
               <Table dataSource={productList} columns={columns} />;
             </Card>
             <Card title="Product Lists" style={{ width: '100%', height : '50%' }}>
               <Table dataSource={productOrderList} columns={rangeColumns} />;
             </Card>
             <Card title="calculate Price" style={{ width: '100%', height : '25%' }}>

               <Form ref={this.formRef} name="control-ref" onFinish={onFinish} style={{ width: '30%'}}>
                 <div>
                 <Form.Item
                   name="id"
                   label="Product"
                   rules={[
                     {
                       required: true,
                     },
                   ]}
                 >
                   <Select
                     placeholder="Select a product"
                     allowClear
                   >
                     {Object.values(productList).map(item => {
                       return (
                         <Option value={item.id}>{item.productName}</Option>
                       )
                     })}
                   </Select>
                 </Form.Item>
                 <Form.Item
                   name="quantity"
                   label="Quantity"
                   rules={[
                     {
                       required: true,
                     },
                   ]}
                 >
                   <Input />
                 </Form.Item>
                 </div>
                 <Form.Item >
                   <Space>
                   <Button type="primary" htmlType="submit" >
                     Submit
                   </Button>

                   </Space>
                 </Form.Item>
               </Form>


               <Input placeholder="Submit the form to find the Price" value={price} style={{ width: '30%'}}/>
             </Card>

           </Content>
           <Footer>Footer</Footer>
         </Layout>
       </Layout>
     </div>
    );
  }
}

export default Products;
