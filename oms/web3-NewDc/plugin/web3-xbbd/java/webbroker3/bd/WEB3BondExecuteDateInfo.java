head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteDateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������(WEB3BondExecuteDateInfo.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
                      2006/10/08 ���� (���u) �d�l�ύX�E���f��099�A110
 Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�E���f��262
 */

package webbroker3.bd;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������)<BR>
 * ���������<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondExecuteDateInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondExecuteDateInfo.class);

    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date bizDate;

    /**
     * (���n������)<BR>
     * ���n������<BR>
     */
    private Date foreignBizDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    private Date executeDate;

    /**
     * (���n����)<BR>
     * ���n����<BR>
     */
    private Date foreignExecuteDate;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    private Date deliveryDate;

    /**
     * (���n��n��)<BR>
     * ���n��n��<BR>
     */
    private Date foreignDeliveryDate;

    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date paymentDate;

    /**
     * (���������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44C7562D0261
     */
    public WEB3BondExecuteDateInfo()
    {

    }

    /**
     * (get������)<BR>
     * get������<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F79600C8
     */
    public Date getBizDate()
    {
        return this.bizDate;
    }

    /**
     * (get���n������)<BR>
     * get���n������<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7A3030A
     */
    public Date getForeignBizDate()
    {
        return this.foreignBizDate;
    }

    /**
     * (get����)<BR>
     * get����<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AC005B
     */
    public Date getExecuteDate()
    {
        return executeDate;
    }

    /**
     * (get���n����)<BR>
     * get���n����<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AC005C
     */
    public Date getForeignExecuteDate()
    {
        return this.foreignExecuteDate;
    }

    /**
     * (get��n��)<BR>
     * get��n��<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AD004B
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (get���n��n��)<BR>
     * get���n��n��<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AD004C
     */
    public Date getForeignDeliveryDate()
    {
        return this.foreignDeliveryDate;
    }

    /**
     * (get������)<BR>
     * get������<BR>
     * @@return Date
     */
    public Date getPaymentDate()
    {
        return this.paymentDate;
    }

    /**
     * (set������)<BR>
     * set������<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@roseuid 44C6F7D40125
     */
    public void setBizDate(Date l_datBizDate)
    {
        this.bizDate = l_datBizDate;
    }

    /**
     * (set���n������)<BR>
     * set���n������<BR>
     * @@param l_datForeignBizDate - (���n������)<BR>
     * ���n������<BR>
     * @@roseuid 44C6F7F0000C
     */
    public void setForeignBizDate(Date l_datForeignBizDate)
    {
        this.foreignBizDate = l_datForeignBizDate;
    }

    /**
     * (set����)<BR>
     * set����<BR>
     * @@param l_datExecuteDate - (����)<BR>
     * ����<BR>
     * @@roseuid 44C6F82601EF
     */
    public void setExecuteDate(Date l_datExecuteDate)
    {
        this.executeDate = l_datExecuteDate;
    }

    /**
     * (set���n����)<BR>
     * set���n����<BR>
     * @@param l_datForeignExecuteDate - (���n����)<BR>
     * ���n����<BR>
     * @@roseuid 44C6F82601F1
     */
    public void setForeignExecuteDate(Date l_datForeignExecuteDate)
    {
        this.foreignExecuteDate = l_datForeignExecuteDate;
    }

    /**
     * (set��n��)<BR>
     * set��n��<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@roseuid 44C6F82700F5
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (set���n��n��)<BR>
     * set���n��n��<BR>
     * @@param l_datForeignDeliveryDate - (���n��n��)<BR>
     * ���n��n��<BR>
     * @@roseuid 44C6F82700F7
     */
    public void setForeignDeliveryDate(Date l_datForeignDeliveryDate)
    {
         this.foreignDeliveryDate = l_datForeignDeliveryDate;
    }

    /**
     * (set������)<BR>
     * set������<BR>
     * @@param l_datPaymentDate - (������)<BR>
     * ������
     */
    public void setPaymentDate(Date l_datPaymentDate)
    {
        this.paymentDate = l_datPaymentDate;
    }

    /**
     * (validate����)<BR>
     * validate����<BR>
     * <BR>
     * �P�jthis.get�������c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02631<BR>
     * <BR>
     * �Q�jthis.get��n�����c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02336<BR>
     * <BR>
     * �R�jthis.get������this.get��n���̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02528<BR>
     * <BR>
     * �S�j����.������.is�O���� ���� true�̏ꍇ�A <BR>
     * �@@�@@�S�|�P�jthis.get���n���� ���� null �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02529<BR>
     * <BR>
     * �@@  �S�|�Q�jthis.get���n��n�� ���� null�@@�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02530<BR>
     * <BR>
     * �@@�@@�S�|�R�jthis.get���n������this.get���n��n���̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02531<BR>
     *
     * �T�jthis.get���������c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02681<BR>
     *
     * �U�jthis.get�������������̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02693<BR>
     *
     * �V�jthis.get��n�����������̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02689<BR>
     *
     * �W�jthis.get���������c�Ɠ�(T+0)�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02690<BR>
     *
     * �X�jthis.get��������this.get�����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02700<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44C6FF2703DC
     */
    public void validateExecuteDate(WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecuteDate(WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�jthis.get�������c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B
        Timestamp l_tisExecuteDate = new Timestamp(this.getExecuteDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisExecuteDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02631,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������c�Ɠ��ł��B");
        }

        // �Q�jthis.get��n�����c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B
        Timestamp l_tisDeliveryDate = new Timestamp(this.getDeliveryDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisDeliveryDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n������c�Ɠ��ł��B");
        }

        //�R�jthis.get������this.get��n���̏ꍇ�A��O���X���[����B
        if (this.getExecuteDate().compareTo(this.getDeliveryDate()) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02528,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������n���𒴂��Ă��܂��B");
        }

        //�S�j����.������.is�O���� ���� true�̏ꍇ
        if (l_bondProduct.isForeignBond())
        {
            //    �S�|�P�jthis.get���n���� ���� null �̏ꍇ�A��O���X���[����B
            if (this.getForeignExecuteDate() == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02529,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���n���������w��ł��B");
            }

            //   4�|�Q�jthis.get���n��n�� ���� null�@@�̏ꍇ�A��O���X���[����B
            if (this.getForeignDeliveryDate() == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02530,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���n��n�������w��ł��B");
            }

            //�@@�S�|�R�jthis.get���n������this.get���n��n���̏ꍇ�A��O���X���[����B
            if (this.getForeignExecuteDate().compareTo(this.getForeignDeliveryDate()) > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02531,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���n���������n��n���𒴂��Ă��܂��B");
            }
        }

        //�T�jthis.get���������c�Ɠ��ł͂Ȃ��ꍇ�A��O���X���[����B
        Timestamp l_tisPaymentDate = new Timestamp(this.getPaymentDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisPaymentDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02681,
                this.getClass().getName() + STR_METHOD_NAME,
                "����������c�Ɠ��ł��B");
        }

        //�U�jthis.get�������������̏ꍇ�A��O���X���[����B
        if (this.getExecuteDate().compareTo(this.getBizDate()) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02693,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������������z���Ă��܂��B");
        }
        
        //�V�jthis.get��n�����������̏ꍇ�A��O���X���[����B
        if (this.getDeliveryDate().compareTo(this.getBizDate()) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02689,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n���͔������ȍ~�ɂ��ĉ������B");
        }
        
        //�W�jthis.get���������c�Ɠ�(T+0)�̏ꍇ�A��O���X���[����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        if (this.getPaymentDate().compareTo(l_datBizDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02690,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������͓����ȍ~�ɂ��ĉ������B");
        }
        
        //�X�jthis.get��������this.get�����̏ꍇ�A��O���X���[����B
        if (this.getPaymentDate().compareTo(this.getExecuteDate()) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02700,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������͖����ȍ~�ɂ��ĉ������B");
        }
    }
}
@
