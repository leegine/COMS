head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityAssetChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����ۗL�ϓ�(WEB3TPSecurityAssetChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�����ۗL�ϓ�)
 */
public class WEB3TPSecurityAssetChange
    extends WEB3TPSecurityChange
{

    /**
     * (�����V�����敪)<BR>
     * ��n���ʕۗL���Y�e�[�u��.�����V�����敪="1"�̏ꍇ�Atrue���Z�b�g�B<BR>
     * �ȊO�̏ꍇ�Afalse�����V�����敪 <BR>
     */
    private boolean splitNewStock = false;

    /**
     * (������)<BR>
     */
    private Date OriginalExecDate;
    
    /**
     * @@roseuid 41087D8103AC
     */
    public WEB3TPSecurityAssetChange()
    {

    }

    /**
     * (create�����ۗL�ϓ�)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityAssetChange
     * @@roseuid 40D8160C00B9
     */
    public static WEB3TPSecurityAssetChange create()
    {
        return new WEB3TPSecurityAssetChange();
    }

    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ��J�n���A�ϓ��I������<BR>
     * �����ۗL�ϓ�.calc�ϓ����f��(��n���ʕۗL���Y�e�[�u��.��n��)�Őݒ肷��B<BR>
     * <BR>
     * �ϓ����f�J�n���F<BR>
     * �@@[�����V���敪 == true ���� �a��敪 == "1:��p"�̏ꍇ]<BR>
     * �ϓ����f�J�n�� = ������<BR>
     * <BR>
     * �A[��L�ȊO]<BR>
     * �@@a)[��n���ʕۗL���Y�e�[�u��.��n�� > �c�Ɠ�(T+5)�̏ꍇ]<BR>
     * �@@�@@�ϓ����f�J�n�� = �c�Ɠ�(T+5)<BR>
     * �@@b)[a)�ȊO]<BR>
     * �@@�@@�ϓ����f�J�n�� =�@@��n���ʕۗL���Y�e�[�u��.��n��<BR>
     * <BR>
     * �ϓ����f�I�����F<BR>
     * �ϓ����f�I���� = �c�Ɠ�(T+5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        //[�����V���敪 == true ���� �a��敪 == "1:��p"�̏ꍇ]
        if(this.isSplitNewStock() == true
            && WEB3TPDepositTypeDef.SUBSTITUTE.equals(this.getDepositType()) == true)
        {
            //�ϓ����f�J�n�� = ������
            setReflectStartDay(this.getOriginalExecDate());
        }
        else if(l_datDeliveryDate.getTime() > getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5).getTime())
        {
            setReflectStartDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
        }

        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * (is�����V�����敪)<BR>
     * <BR>
     * this.�����V�����敪��ԋp����B<BR>
     * <BR>
     */
    public boolean isSplitNewStock()
    {
        return splitNewStock;
    }

    /**
     * (set�����V�����敪)<BR>
     * <BR>
     * ����.�����V�����敪��this.�����V�����敪�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_splitNewStock - (�����V�����敪)
     */
    public void setSplitNewStock(boolean l_splitNewStock)
    {
        splitNewStock = l_splitNewStock;
    }

    /**
     * (get������)<BR>
     * <BR>
     * this.��������ԋp����B<BR>
     * <BR>
     */
    public Date getOriginalExecDate()
    {
        return OriginalExecDate;
    }

    /**
     * (set������)<BR>
     * <BR>
     * ����.��������this.�������ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_OriginalExecDate - (������)
     */
    public void setOriginalExecDate(Date l_OriginalExecDate)
    {
        OriginalExecDate = l_OriginalExecDate;
    }

    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("splitNewStock", splitNewStock)
            .toString();
    }
}
@
