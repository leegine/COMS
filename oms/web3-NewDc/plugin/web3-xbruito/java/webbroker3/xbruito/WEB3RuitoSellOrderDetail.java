head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellOrderDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ�����񒍕����׃I�u�W�F�N�g(WEB3RuitoSellOrderDetail)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/08  ���u�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;
import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;
/**
 * �ݐϓ�����񒍕����׃I�u�W�F�N�g<BR>�@@
 */
public class WEB3RuitoSellOrderDetail
{
	private static WEB3LogUtility log =
	    WEB3LogUtility.getInstance(WEB3RuitoSellOrderDetail.class);

	/**
	 * ��������<BR>
	 */
	private Timestamp orderTime;
	/**
	 *  1:��t��(�V�K����)�@@ 3:������(�V�K����)�@@�@@<BR>�@@
	 *   6:�������s(�V�K����) 12:��t��(�������)�@@<BR>�@@
	 * 14:������(�������)�@@�@@�@@ 15:�������s(�������) <BR>�@@
	 * 30:MRF����G���[�@@�@@�@@31:��t��(MRF���L��)�@@<BR>�@@
	 *  32:������(MRF���L��) <BR>�@@
	 */
	private String orderStatusDiv;
	/**
	 * �������ʃ^�C�v<BR>
	 * 1:�����@@2:���z<BR>
	 */
	private String orderQuantityType;
	/**
	 * ��������<BR>
	 */
	private double orderQuantity;
	/**
	 * this.�󒍓�����Ԃ��B<BR>
	 * @@return Timestamp
	 * @@roseuid 40766C38021C
	 */
	public Timestamp getOrderTime()
	{
		log.debug("[getOrderTime] orderTime = " + orderTime);
		return orderTime;
	}
	/**
	 * �������Ԃ̐ݒ���s��<BR>
	 * @@param l_orderTime - ��������<BR>
	 * @@roseuid 40766C47021C
	 */
	public void setOrderTime(Timestamp l_orderTime)
	{
		log.debug("[setOrderTime] l_orderTime = " + l_orderTime);
		orderTime = l_orderTime;
	}
	/**
	 * this.������ԋ敪��Ԃ��B<BR>
	 * @@return String
	 * @@roseuid 40766C560076
	 */
	public String getOrderStatusDiv()
	{
		log.debug("[getOrderStatusDiv] orderStatusDiv = " + orderStatusDiv);
		return orderStatusDiv;
	}
	/**
	 * ������ԋ敪�̐ݒ���s��<BR>
	 * @@param l_strOrderStatusDiv - ������ԋ敪<BR>
	 * @@roseuid 40766C6F0374
	 */
	public void setOrderStatusDiv(String l_strOrderStatusDiv)
	{
		log.debug("[setOrderStatusDiv] l_strOrderStatusDiv = " + l_strOrderStatusDiv);
		orderStatusDiv = l_strOrderStatusDiv;
	}
	/**
	 * this.�������ʃ^�C�v��Ԃ��B<BR>
	 * @@return String
	 * @@roseuid 40A330F703A8
	 */
	public String getOrderQuantityType()
	{
		log.debug("[getOrderQuantityType] orderQuantityType = " + orderQuantityType);
		return orderQuantityType;
	}
	/**
	 * �������ʃ^�C�v�̐ݒ���s��<BR>
	 * @@param l_strOrderQuantityType
	 * @@roseuid 40A33103035A
	 */
	public void setOrderQuantityType(String l_strOrderQuantityType)
	{
		log.debug("[setOrderQuantityType] l_strOrderQuantityType = " + l_strOrderQuantityType);
		orderQuantityType = l_strOrderQuantityType;
	}
	/**
	 * this.�������ʂ�Ԃ��B<BR>
	 * @@return double
	 * @@roseuid 40766C8E02A9
	 */
	public double getOrderQuantity()
	{
		log.debug("[getOrderQuantity] orderQuantity = " + orderQuantity);
		return orderQuantity;
	}
	/**
	 * �������ʂ̐ݒ���s��<BR>
	 * @@param l_dblOrderQuantity - ��������<BR>.
	 * @@roseuid 40766C830345
	 */
	public void setOrderQuantity(double l_dblOrderQuantity)
	{
		log.debug("[setOrderQuantity] l_dblOrderQuantity = " + l_dblOrderQuantity);
		orderQuantity = l_dblOrderQuantity;
	}
}
@
