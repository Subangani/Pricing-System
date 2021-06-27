import React, {Component} from 'react';
import {connect} from 'dva';
import {Layout, Space, Card, Table, Select, Form, Input, Button} from 'antd';

const {Header, Footer, Sider, Content} = Layout;
const {Option} = Select;


@connect(({productInfo, loading}) => ({
  productInfo,
  loading: loading.effects['productInfo/fetch'],
}))
class Products extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const {
      loading
    } = this.props;
    const {productList, price} = this.props.productInfo;
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
        title: 'Carton Price',
        dataIndex: 'cartonPrice',
        key: 'cartonPrice',
      },
    ];
    const onFinish = (values) => {
      const {dispatch} = this.props;
      dispatch({
        type: 'productInfo/calculatePrice',
        payload: {'requestedProduct': [values]}
      });

    };

    const onReset = () => {
      this.formRef.current.resetFields();
    };

    return (
      <div>
        <Card title="calculate Price" style={{width: '100%', height: '50%'}}>

          <Form ref={this.formRef} name="control-ref" onFinish={onFinish} style={{width: '30%'}}>
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
              <Input/>
            </Form.Item>

            <Form.Item>
              <Space>


                <Button type="primary" htmlType="submit">
                  Submit
                </Button>

                <Button htmlType="button" onClick={this.onReset}>
                  Reset
                </Button>
              </Space>
            </Form.Item>
            <Input placeholder="Submit the form to find the Price" value={price} style={{width: '500'}}/>

          </Form>

        </Card>
      </div>
    );
  }
}
