head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMarketLinkStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������s��A����(WEB3FeqMarketLinkStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/
    
package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FeqOrderRequestDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������s��A����)<BR>
 * �O�������s��A���󋵃N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3FeqMarketLinkStateUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3FeqMarketLinkStateUnit.class);
     
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�s�ꖼ)<BR>
     * �s�ꖼ<BR>
     */
    public String marketName;
    
    /**
     * (�O�������s��A���敪)<BR>
     * �O�������s��A���敪<BR>
     */
    public String feqMarketLinkDiv;
    
    /**
     * (�ύX��O�������s��A���敪)<BR>
     * �ύX��O�������s��A���敪<BR>
     */
    public String afterFeqMarketLinkDiv;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     *<BR>
     * �P�j�s��R�[�h�`�F�b�N<BR> 
     * �@@this.�s��R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00443<BR> 
     * <BR>
     * �Q�j�O�������s��A���敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�O�������s��A���敪 == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02653<BR>
     *  <BR>
     * �@@�Q�|�Q�jthis.�O�������s��A���敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     *�@@�@@�@@�@@�u�O�������s��A���敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�@@�@@�E0�F��A���iMAIL�j<BR> 
     *�@@�@@�@@�@@�E1�F�A���iMQ�j<BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02654<BR> 
     *  <BR>
     * �R�j�ύX��O�������s��A���敪�`�F�b�N<BR> 
     * �@@�R�|�P�jthis.�ύX��O�������s��A���敪 == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02653<BR> 
     *  <BR>
     * �@@�R�|�Q�jthis.�ύX��O�������s��A���敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     *�@@�@@�@@�@@�u�O�������s��A���敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�@@�@@�E0�F��A���iMAIL�j  <BR>
     *�@@�@@�@@�@@�E1�F�A���iMQ�j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02654<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�s��R�[�h�`�F�b�N 
        // this.�s��R�[�h == null�̏ꍇ�A��O���X���[����B 
        if (this.marketCode == null)
        {
            log.debug("�s��R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s��R�[�h�����w��ł��B" + this.marketCode);
        }
        
        // �Q�j�O�������s��A���敪�`�F�b�N 
        // �Q�|�P�jthis.�O�������s��A���敪 == null�̏ꍇ�A��O���X���[����B
        if (this.feqMarketLinkDiv == null)
        {
            log.debug("�O�������s��A���敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02653,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������s��A���敪�����w��ł��B" + this.feqMarketLinkDiv);
        }
        
        //�Q�|�Q�jthis.�O�������s��A���敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�O�������s��A���敪������`�̒l�v�̗�O���X���[����B 
        //�@@�@@�E0�F��A���iMAIL�j 
        //�@@�@@�E1�F�A���iMQ�j 
        if ((!WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(this.feqMarketLinkDiv)) 
            &&(!WEB3FeqOrderRequestDivDef.REQUEST_MQ.equals(this.feqMarketLinkDiv)))
        {
            log.debug("�O�������s��A���敪������`�̒l�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02654,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������s��A���敪������`�̒l�B" + this.feqMarketLinkDiv);
        }
        
        //�R�j�ύX��O�������s��A���敪�`�F�b�N 
        //�R�|�P�jthis.�ύX��O�������s��A���敪 == null�̏ꍇ�A��O���X���[����B 
        if (this.afterFeqMarketLinkDiv == null)
        {
            log.debug("�O�������s��A���敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02653,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������s��A���敪�����w��ł��B" + this.afterFeqMarketLinkDiv);
        }
        
        //�@@�R�|�Q�jthis.�ύX��O�������s��A���敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�O�������s��A���敪������`�̒l�v�̗�O���X���[����B 
        //�@@�@@�E0�F��A���iMAIL�j 
        //�@@�@@�E1�F�A���iMQ�j
        if ((!WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(this.afterFeqMarketLinkDiv)) 
            &&(!WEB3FeqOrderRequestDivDef.REQUEST_MQ.equals(this.afterFeqMarketLinkDiv)))
        {
            log.debug("�O�������s��A���敪������`�̒l�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02654,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������s��A���敪������`�̒l�B" + this.afterFeqMarketLinkDiv);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�O�������s��A����)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqMarketLinkStateUnit()
    {
        
    }
}
@
