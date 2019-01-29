head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPServiceChargeRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPServiceChargeRestraint.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/91/26 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�L���T�[�r�X���p�S����)
 */
public class WEB3TPServiceChargeRestraintReflector extends WEB3TPRestraintReflector
{

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPServiceChargeRestraintReflector.class);
    
    /**
     * (��t����)<BR>
     */
    private Date acceptedDate;
    
    
   /**
    * @@roseuid 41F7479B0141
    */
   public WEB3TPServiceChargeRestraintReflector() 
   {
    
   }
   
   /**
    * (create�L���T�[�r�X���p�S����)
    * �L���T�[�r�X���p�S�������쐬���A�ԋp����B
    * @@param l_calcCondition - (�]�͌v�Z����)
    * @@param l_dblAmount - (��n���)
    * @@param l_datFinTransactionDate - (�g�����U�N�V����������)
    * @@param l_datDeliveryDate - (��n��)
    * @@roseuid 41F0AABB00A4
    */
   public static WEB3TPServiceChargeRestraintReflector create(WEB3TPCalcCondition l_calcCondition, double l_dblAmount, Date l_datDeliveryDate, Date l_datAcceptedDate) 
   {
       WEB3TPServiceChargeRestraintReflector l_instance = new WEB3TPServiceChargeRestraintReflector();
       l_instance.setCalcCondition(l_calcCondition);
       l_instance.setAmount(l_dblAmount);
       l_instance.setAcceptedDate(l_datAcceptedDate);
       l_instance.calcReflectDay(l_datDeliveryDate);
       return l_instance;       
   }

	/**
    * (get��t����)<BR>
	 * @@return acceptedDate ��߂��܂��B
	 */
	public Date getAcceptedDate() {
		return acceptedDate;
	}
	/**
    * (set��t����)<BR>
	 * @@param acceptedDate acceptedDate ��ݒ�B
	 */
	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
   
   /**
    * (calc�ϓ����f��)<BR>
    * �ϓ����f�J�n���A�ϓ����f�I������<BR>
    * �ȉ��̂悤�ɃZ�b�g����B<BR>
    * <BR>
    * �ϓ����f�J�n����T+0<BR>
    * �ϓ����f�I����������.��n��-1<BR>
    * @@param l_datDeliveryDate - (��n��)
    * @@roseuid 41F0AAC80084
    */
   public void calcReflectDay(Date l_datDeliveryDate)
   {   	
       final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

       Date l_datT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
       Date l_datT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
       //��t������null
       //��t��������n������
       //��n����T+0�ȑO
       //��t��������n������n�����s�{�T�ȑO
       //�̃f�[�^�̓��[�h����Ȃ��͂�
       if ((this.acceptedDate == null) ||
       		(WEB3DateUtility.compareToDay(acceptedDate, l_datDeliveryDate) > 0) ||
       		(WEB3DateUtility.compareToDay(l_datT0, l_datDeliveryDate) >= 0) ||
			((WEB3DateUtility.compareToDay(acceptedDate, l_datDeliveryDate) == 0) &&
					(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)))
       {
           throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
       }
       
       
       //��t������
       //T+5�ȑO�̏ꍇ
       if(WEB3DateUtility.compareToDay(l_datT5, acceptedDate) >= 0)
       {
       		
        //��t������T+0�ȑO�̏ꍇ
       		if(WEB3DateUtility.compareToDay(l_datT0, acceptedDate) >= 0)
       		{
       	        //�ϓ����f�J�n����T+0
       	        this.setReflectStartDay(l_datT0);       	       			       			
       		}
       		else
       		{
       	        //�ϓ����f�J�n������t����
       	        this.setReflectStartDay(acceptedDate);       	       			
       		}
       }
       else
       {
        //�ϓ����f�J�n������t����
        this.setReflectStartDay(l_datT5);       	       	
       }
       
       
       //��n����
       //T+5�ȑO�̏ꍇ
       if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)
       {
	        //�ϓ����f�I����������.��n��-1<BR>
	        this.setReflectEndDay(this.getCalcCondition().rollBizDate(l_datDeliveryDate, -1));
       }
       //T+5����̏ꍇ
       else
       {
	        //�ϓ����f�I������T+5<BR>
	        this.setReflectEndDay(l_datT5);       	
       }
       	

   }
   
   /**
    * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
    * 
    * @@return String
    */
   public String toString()
   {
       String l_strYYYYMMDDFormat = "yyyy/MM/dd";

       return ToStringUtils
           .newToStringBuilder(this)
           .append("acceptedDate", WEB3DateUtility.formatDate(this.getAcceptedDate(), l_strYYYYMMDDFormat))
           .appendSuper(super.toString())
           .toString();
   }
   
}
@
