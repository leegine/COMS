head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�ϓ��N���X(WEB3TPAssetReflector)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/07/30 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (���Y�ϓ�) <BR>
 * ���Y�ϓ���\������B
 */
public abstract class WEB3TPAssetReflector
{

    /**
     * �ϓ����f�J�n�� <BR>
     */
    private Date reflectStartDay;

    /**
     * �ϓ����f�I���� <BR>
     */
    private Date reflectEndDay;

    /**
     * �v�Z����
     */
    private WEB3TPCalcCondition calcCondition;

    /**
     * @@roseuid 4136AE000065
     */
    public WEB3TPAssetReflector()
    {

    }

    /**
     * (calc�ϓ����f��)
     * �ϓ����f���̌v�Z�̒��ے�`<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�����Ɍv�Z���ʂ�ݒ肷��<BR>
     * @@param l_datDeliveryDate ��n��
     * @@roseuid 40D92B4A02A8
     */
    public abstract void calcReflectDay(Date l_datDeliveryDate);

    /**
     * (get�ϓ����f�J�n��)<BR>
     * <BR>
     * �ϓ����f�J�n�����擾����B<BR>
     * @@return java.util.java.util.Date
     * @@roseuid 40EE6CFA0190
     */
    public Date getReflectStartDay()
    {
        return this.reflectStartDay;
    }

    /**
     * (set�ϓ����f�J�n��)<BR>
     * <BR>
     * �ϓ����f�J�n�����Z�b�g����B<BR>
     * @@param l_datReflectStartDay - �ϓ����f�J�n��
     * @@roseuid 40EE6D1301ED
     */
    public void setReflectStartDay(Date l_datReflectStartDay)
    {
        this.reflectStartDay = l_datReflectStartDay;
    }

    /**
     * (get�ϓ����f�I����)<BR>
     * <BR>
     * �ϓ����f�I�������擾����B<BR>
     * @@return java.util.java.util.Date
     * @@roseuid 40EE6D0B01AF
     */
    public Date getReflectEndDay()
    {
        return this.reflectEndDay;
    }

    /**
     * (set�ϓ����f�I����)<BR>
     * <BR>
     * �ϓ����f�I�������Z�b�g����B<BR>
     * @@param l_datReflecEndDay - �ϓ����f�J�I����
     * @@roseuid 40EE6D180047
     */
    public void setReflectEndDay(Date l_datReflectEndDay)
    {
        this.reflectEndDay = l_datReflectEndDay;
    }

    /**
     * (is�ϓ����f���Ԓ�)<BR>
     * �ϓ����ԓ��𔻒f����B<BR>
     * �ϓ����f�J�n��<= l_datBizDate <= �ϓ����f�I�����̏ꍇ�Atrue��Ԃ��B<BR>
     * ���̈ȊO�ꍇ�Afalse��Ԃ��B<BR>
     * @@param l_datBizDate- �c�Ɠ�<BR>
     * @@return boolean
     * @@roseuid 40D92F7E01CD
     */
    public boolean isDuringReflectTime(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isDuringReflectTime(Date l_datBizDate)";

        if (l_datBizDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        long l_lngBizDate = l_datBizDate.getTime();
        if ( (getReflectStartDay().getTime() <= l_lngBizDate)
            && (l_lngBizDate <= getReflectEndDay().getTime()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get�v�Z����)
     * �v�Z�������擾����B<BR>
     * @@return WEB3TPCalcCondition
     * @@roseuid 4136AC6403CB
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return calcCondition;
    }

    /**
     * (set�v�Z����)
     * �v�Z������ݒ肷��B
     * @@param l_calcCondition - �]�͌v�Z����
     * @@roseuid 4136AC650022
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        calcCondition = l_calcCondition;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";
        String l_datStartDay = WEB3DateUtility.formatDate(getReflectStartDay(), l_strYYYYMMDDFormat);
        String l_datEndDay = WEB3DateUtility.formatDate(getReflectEndDay(), l_strYYYYMMDDFormat);

        return ToStringUtils
            .newToStringBuilder(this)
            .append("reflectStartDay", l_datStartDay)
            .append("reflectEndDay", l_datEndDay)
            .toString();
    }

}
@
