head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������(WEB3FeqExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O�����������)<BR>
 * �O�����������N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqExecuteUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExecuteUnit.class);
        
    /**
     * (���P��)<BR>
     * ���P��
     */
    public String execPrice;
    
    /**
     * (��萔��)<BR>
     * ��萔��
     */
    public String execQuantity;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date executionTimestamp;
    
    /**
     * (�O�����������)<BR>
     * �R���X�g���N�^
     * @@roseuid 4201C0F10303
     */
    public WEB3FeqExecuteUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���P���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02021<BR>
     * �@@�P�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02022<BR>
     * �@@�P�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02023<BR>
     * �@@�P�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔�<BR>
     * �@@�O�ł���΁A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02024<BR>
     * <BR>
     * �Q�j�@@��萔�ʂ̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02025<BR>
     * �@@�Q�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02026<BR>
     * �@@�Q�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02186<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428D710F0270
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���P���̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.execPrice))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���P���������͂ł��B"); 
        }
        
        //�P�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����
        if (!WEB3StringTypeUtility.isNumber(this.execPrice))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���P�������l�ȊO�̒l�ł�:" + this.execPrice); 
        }
        
        //�P�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.execPrice) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���P�������l�ɕϊ������l <= 0�ł�:" + this.execPrice); 
        }
        
        //�P�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔�
        //�O�ł���΁A��O���X���[����B
        int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.execPrice);
        int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.execPrice);        
        if (l_intIntCnt > 6 || l_intFacCnt > 5)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���P���͐������U���C�������T���͈̔͊O�ł�:" + this.execPrice); 
        }

        //�Q�j�@@��萔�ʂ̃`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.execQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                " ��萔�ʂ������͂ł��B"); 
        }

        //�Q�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isInteger(this.execQuantity))
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.execQuantity) > 9)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ��萔�ʂ�9���ȓ��̐����l�ł͂���܂���:" + this.execQuantity); 
            }
            
            //�Q�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.execQuantity) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ��萔�ʂ����l�ɕϊ������l <= 0�ł�:" + this.execQuantity); 
            }            
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                " ��萔�ʂ������l�ł͂���܂���:" + this.execQuantity); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
