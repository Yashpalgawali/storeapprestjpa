package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

//@Entity
//	@Table(name="tbl_temp_po_products")
	@SequenceGenerator(name="po_temp_products_seq",initialValue = 1, allocationSize = 1)
	public class PurchaseOrderTempProducts {

		@Id
		@GeneratedValue(generator = "po_temp_products_seq",strategy = GenerationType.AUTO)
		private Integer purchase_prod_order_id;
		
		private Integer qty;
		
		private Integer temp_id;
		
		private Integer cgst_per;
		
		private Integer sgst_per;
		
		private Integer igst_per;
		
		private Integer cgst;
		 
		private Integer sgst;
		
		private Integer igst;
		
		private float unit_price;
		
		private float total;
		
		@Transient
		private String stoption;
		
		@OneToOne(fetch = FetchType.EAGER)
//		@JsonIgnore
		@JoinColumn(name="prod_id")
		private PoProductsList product;

		public Integer getPurchase_prod_order_id() {
			return purchase_prod_order_id;
		}

		public void setPurchase_prod_order_id(Integer purchase_prod_order_id) {
			this.purchase_prod_order_id = purchase_prod_order_id;
		}

		public Integer getQty() {
			return qty;
		}

		public void setQty(Integer qty) {
			this.qty = qty;
		}

		public Integer getTemp_id() {
			return temp_id;
		}

		public void setTemp_id(Integer temp_id) {
			this.temp_id = temp_id;
		}

		public Integer getCgst_per() {
			return cgst_per;
		}

		public void setCgst_per(Integer cgst_per) {
			this.cgst_per = cgst_per;
		}

		public Integer getSgst_per() {
			return sgst_per;
		}

		public void setSgst_per(Integer sgst_per) {
			this.sgst_per = sgst_per;
		}

		public Integer getIgst_per() {
			return igst_per;
		}

		public void setIgst_per(Integer igst_per) {
			this.igst_per = igst_per;
		}

		public Integer getCgst() {
			return cgst;
		}

		public void setCgst(Integer cgst) {
			this.cgst = cgst;
		}

		public Integer getSgst() {
			return sgst;
		}

		public void setSgst(Integer sgst) {
			this.sgst = sgst;
		}

		public Integer getIgst() {
			return igst;
		}

		public void setIgst(Integer igst) {
			this.igst = igst;
		}

		public float getUnit_price() {
			return unit_price;
		}

		public void setUnit_price(float unit_price) {
			this.unit_price = unit_price;
		}

		public float getTotal() {
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}

		public String getStoption() {
			return stoption;
		}

		public void setStoption(String stoption) {
			this.stoption = stoption;
		}

		public PoProductsList getProduct() {
			return product;
		}

		public void setProduct(PoProductsList product) {
			this.product = product;
		}

		/**
		 * @param purchase_prod_order_id
		 * @param qty
		 * @param temp_id
		 * @param cgst_per
		 * @param sgst_per
		 * @param igst_per
		 * @param cgst
		 * @param sgst
		 * @param igst
		 * @param unit_price
		 * @param total
		 * @param stoption
		 * @param product
		 */
		public PurchaseOrderTempProducts(Integer purchase_prod_order_id, Integer qty, Integer temp_id, Integer cgst_per,
				Integer sgst_per, Integer igst_per, Integer cgst, Integer sgst, Integer igst, float unit_price,
				float total, String stoption, PoProductsList product) {
			super();
			this.purchase_prod_order_id = purchase_prod_order_id;
			this.qty = qty;
			this.temp_id = temp_id;
			this.cgst_per = cgst_per;
			this.sgst_per = sgst_per;
			this.igst_per = igst_per;
			this.cgst = cgst;
			this.sgst = sgst;
			this.igst = igst;
			this.unit_price = unit_price;
			this.total = total;
			this.stoption = stoption;
			this.product = product;
		}

		/**
		 * 
		 */
		public PurchaseOrderTempProducts() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "PurchaseOrderTempProducts [purchase_prod_order_id=" + purchase_prod_order_id + ", qty=" + qty
					+ ", temp_id=" + temp_id + ", cgst_per=" + cgst_per + ", sgst_per=" + sgst_per + ", igst_per="
					+ igst_per + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", unit_price=" + unit_price
					+ ", total=" + total + ", stoption=" + stoption + ", product=" + product + "]";
		}
	}
