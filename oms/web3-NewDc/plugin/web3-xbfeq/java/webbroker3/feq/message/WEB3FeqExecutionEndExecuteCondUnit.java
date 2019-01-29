head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionEndExecuteCondUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���I�����{����(WEB3FeqExecutionEndExecuteCondUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O�������o���I�����{����)<BR>
 * �O�������o���I�����{�����N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqExecutionEndExecuteCondUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExecutionEndExecuteCondUnit.class);
        
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (������)<BR>
     * ��ʂɂē��͂��ꂽ������
     */
    public Date orderBizDate;
    
    /**
     * (�O�������o���I�����{����)<BR>
     * �R���X�g���N�^
     * @@roseuid 420208EC01F3
     */
    public WEB3FeqExecutionEndExecuteCondUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02045<BR>
     * <BR>
     * �Q�j�@@�������`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.������ == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00406<BR>
     * �@@�Q�|�Q�j�@@this.���������c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02019<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF022017D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�s��R�[�h�`�F�b�N
        //this.�s��R�[�h == null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " �s��R�[�h�����w��ł��B"); 
        }
        
        //�Q�j�@@�������`�F�b�N
        //�Q�|�P�j�@@this.������ == null�̏ꍇ�A��O���X���[����B
        if (this.orderBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����������w��ł��B"); 
        }

        //�Q�|�Q�j�@@this.���������c�Ɠ��łȂ��ꍇ�A��O���X���[����B
        //WEB3-XBFEQ-A-CD-0028
        String l_strFlag1 = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(this.orderBizDate.getTime()));
        String l_strFlag2 = WEB3GentradeTradingTimeManagement.getFeqBizDateType(
            new Timestamp(this.orderBizDate.getTime()));

        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag1) || 
            WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag2))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                " �������͉c�Ɠ��ł͂���܂���B" + this.orderBizDate); 
        }
        
        log.exiting(STR_METHOD_NAME);  
    }
}
@
