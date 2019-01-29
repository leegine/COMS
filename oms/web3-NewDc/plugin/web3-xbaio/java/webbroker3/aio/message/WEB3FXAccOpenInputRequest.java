head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݓ��̓��N�G�X�g(WEB3FXAccOpenInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.865
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�����J�ݓ��̓��N�G�X�g) <BR>
 * FX�����J�ݓ��̓��N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX������ӎ�����ꗗ) <BR>
     * FX������ӎ�����̈ꗗ <BR>
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;

    /**
     * (�d�q���`�F�b�N�t���O) <BR>
     * �d�q���`�F�b�N�t���O  <BR>
     * �d�q���V�X�e���֐ڑ����s������ݒ肷��B<BR> 
     * <BR>
     * true�F�ڑ�������B<BR> 
     * false�F�ڑ������Ȃ��B<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (��ʃR�[�h) <BR>
     * ��ʃR�[�h <BR>
     */
    public String typeCode;

    /**
     * (���ʃR�[�h) <BR>
     * ���ʃR�[�h<BR>
     */
    public String[] requestCode;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E784690232
     */
    public WEB3FXAccOpenInputRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j FX������ӎ�����`�F�b�N <BR>
     * this.FX������ӎ�����ꗗ��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �Q�j FX������ӎ�������e�`�F�b�N <BR>
     * thisFX������ӎ�����ꗗ�̗v�f���ƂɈȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �EFX������ӎ�����.����ԍ���null�̏ꍇ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �EFX������ӎ�����.����񓚁�null�̏ꍇ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �d�q���ڑ��`�F�b�N�t���O==true�̏ꍇ�ȉ��́i�R�j�`�i�S�j�̃`�F�b�N���s���B<BR> 
     * <BR>
     * �R�j�@@��ʃR�[�h�`�F�b�N<BR> 
     * this.��ʃR�[�h��null�̏ꍇ�A��O��throw����B<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02202 <BR>
     * �S�j�@@���ʃR�[�h�`�F�b�N<BR> 
     * this.���ʃR�[�h�̗v�f�����A�u�O�v�̏ꍇ�A��O��throw����B<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00829 <BR>
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j FX������ӎ�����`�F�b�N 
        // this.FX������ӎ�����ꗗ��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (fxTradeAgreementList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ��null"); 
        }
        
        // �Q�j FX������ӎ�������e�`�F�b�N 
        // thisFX������ӎ�����ꗗ�̗v�f���ƂɈȉ��̃`�F�b�N���s���B  
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B  
        for (int i = 0; i < fxTradeAgreementList.length; i++)
        {
            // �EFX������ӎ�����.����ԍ���null�̏ꍇ  
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309 
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionNumber))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ.����ԍ���null"); 
            }

            // �EFX������ӎ�����.����񓚁�null�̏ꍇ  
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionAnswer))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ.����񓚁�null"); 
            }
        }
        
        //�d�q���ڑ��`�F�b�N�t���O==true�̏ꍇ�ȉ��́i�R�j�`�i�S�j�̃`�F�b�N���s���B 
        if (batoCheckFlag)
        {
            //�R�j�@@��ʃR�[�h�`�F�b�N 
            //this.��ʃR�[�h��null�̏ꍇ�A��O��throw����B
            if (WEB3StringTypeUtility.isEmpty(typeCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    this.getClass().getName() + "." + l_strMethodName,
                    "��ʃR�[�h�����w��ł��B"); 
            }
            
            //�S�j�@@���ʃR�[�h�`�F�b�N 
            //this.���ʃR�[�h�̗v�f�����A�u�O�v�̏ꍇ�A��O��throw����B 
            if (requestCode == null || requestCode.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���ʃR�[�h�����w��ł��B"); 
            }
        }
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690271
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenInputResponse(this);
    }
}@
