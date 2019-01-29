head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginRepaymentUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ٍσN���X(WEB3MarginRepaymentUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����ٍρj�B<br>
 * <br>
 * �M�p����ٍσN���X
 * @@version 1.0
 */
public class WEB3MarginRepaymentUnit extends Message 
{ 
   /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginRepaymentUnit.class);    

    
    /**
     * (�ٍϋ敪)<BR>
     * 1�F���x�M�p�@@2�F��ʐM�p<BR>
     */
    public String repaymentDiv;
    
    /**
     * (�ٍϊ����l)<BR>
     * ���w��B<BR>
     * �������̏ꍇ�h9999999�h<BR>
     */
    public String repaymentTimeLimit;
    
    /**
     * (�M�p����ٍ�)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.margin.message.WEB3MarginRepaymentUnit
     * @@roseuid 40C930D40333
     */
    public WEB3MarginRepaymentUnit() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�ٍϋ敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�ٍϋ敪��null�ł���΁u�ٍϋ敪��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00628<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�ٍϋ敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ٍϋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F���x�M�p�h<BR>
     * �@@�@@�@@�@@�E�h2�F��ʐM�p�h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00629<BR>
     * <BR>
     * �Q�j�@@�ٍϊ����l�`�F�b�N<BR>
     * �@@this.�ٍϊ����l���ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@ �@@ �Enull<BR>
     * �@@�@@�@@�@@�@@�u�ٍϊ����l��null�v�̗�O���X���[�B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00630<BR>
     * <BR>
     * �@@�@@�@@�E�����ȊO<BR>
     * �@@�@@�@@�@@�@@�u�ٍϊ����l�������ȊO�v�̗�O���X���[�B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00631<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�ٍϊ����l <= �O<BR>
     * �@@�@@�@@�@@�@@�u�ٍϊ����l��0�ȉ��v�̗�O���X���[�B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00632<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 407E7FAA01A5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@�ٍϋ敪�`�F�b�N<BR>
        //     * �@@�P�|�P�jthis.�ٍϋ敪��null�ł���΁u�ٍϋ敪��null�v�̗�O���X���[����B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00628<BR>
        //
        if (repaymentDiv == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00628,STR_METHOD_NAME);
        }
        // �@@�P�|�Q�jthis.�ٍϋ敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
        //    * �@@�@@�@@�@@�@@�u�ٍϋ敪������`�̒l�v�̗�O���X���[����B<BR>
        //    * �@@�@@�@@�@@�E�h1�F���x�M�p�h<BR>
        //    * �@@�@@�@@�@@�E�h2�F��ʐM�p�h<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00629<BR>
        //
        if ( !WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(repaymentDiv) && !WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(repaymentDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00629,STR_METHOD_NAME);
        }
        // �Q�j�@@�ٍϊ����l�`�F�b�N<BR>
        //    * �@@this.�ٍϊ����l���ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
        //    * �@@�ȉ��̗�O���X���[����B<BR>
        //    * <BR>
        //    * �@@ �@@ �Enull<BR>
        //    * �@@�@@�@@�@@�@@�u�ٍϊ����l��null�v�̗�O���X���[�B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00630<BR>
        //
        if (repaymentTimeLimit == null )
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00630,STR_METHOD_NAME);
        }
        // �@@�@@�@@�E�����ȊO<BR>
        //    * �@@�@@�@@�@@�@@�u�ٍϊ����l�������ȊO�v�̗�O���X���[�B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00631<BR>
        //
        if (!WEB3StringTypeUtility.isNumber(repaymentTimeLimit))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00631,STR_METHOD_NAME);
        } 
        // �@@�@@�@@�Ethis.�ٍϊ����l <= �O<BR>
        //    * �@@�@@�@@�@@�@@�u�ٍϊ����l��0�ȉ��v�̗�O���X���[�B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00632<BR>
        //
        if (Long.parseLong(repaymentTimeLimit) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00632,STR_METHOD_NAME);
        }       
        log.exiting(STR_METHOD_NAME);
    }
}
@
