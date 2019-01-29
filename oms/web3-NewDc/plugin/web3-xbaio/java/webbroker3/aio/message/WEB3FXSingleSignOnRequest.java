head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �V���O���T�C���I�����N�G�X�g (WEB3FXSingleSignOnRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/25 ���Ō� (���u) �V�K�쐬   
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
 * (�V���O���T�C���I�����N�G�X�g) <BR>
 * F�V���O���T�C���I�����N�G�X�g�N���X<BR>
 * 
 * @@author ���Ō�(���u)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_single_sign_on";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (�d�q���`�F�b�N�t���O) <BR>
     * FX�h�L�������g�{�������`�F�b�N�̗v/�s�v<BR> 
     * <BR>
     * true�F�`�F�b�N�v<BR> 
     * false�F�`�F�b�N�s�v <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (��ʃR�[�h) <BR>
     * FX�h�L�������g�{�������`�F�b�N�����ɂ�����<BR> 
     * �ژ_�����`�F�b�N�̑�����<BR>
     */
    public String typeCode;

    /**
     * (���ʃR�[�h) <BR>
     * FX�h�L�������g�{�������`�F�b�N�����ɂ�����<BR> 
     * �ژ_�����`�F�b�N�̑�����<BR>
     */
    public String[] requestCode;

    /**
     * (�����s�v���������쐬�t���O) <BR>
     * �����s�v���������쐬�̗v�^�s�v <BR>
     * <BR>
     * true�F�쐬�v<BR> 
     * false�F�쐬�s�v<BR>
     */
    public boolean noExplainAgreeHistoryFlag;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E784690232
     */
    public WEB3FXSingleSignOnRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
     * <BR>
     * �d�q���ڑ��`�F�b�N�t���O==true�̏ꍇ<BR>
     * �ȉ��̂P�j�A�Q�j�̃`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�@@��ʃR�[�h�`�F�b�N<BR> 
     * this.��ʃR�[�h==null�̏ꍇ�A��O��throw����B<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02202 <BR>
     * �Q�j�@@���ʃR�[�h�`�F�b�N<BR> 
     * this.���ʃR�[�h ==null or  <BR>
     * this.���ʃR�[�h.length==0�̏ꍇ�A��O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00829 <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�d�q���ڑ��`�F�b�N�t���O==true�̏ꍇ�ȉ��̂P�j�A�Q�j�̃`�F�b�N���s���B  
        if (batoCheckFlag)
        {
            //�P�j�@@��ʃR�[�h�`�F�b�N 
            //this.��ʃR�[�h==null�̏ꍇ�A��O��throw����B 
            if (WEB3StringTypeUtility.isEmpty(typeCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��ʃR�[�h�����w��ł��B"); 
            }
            
            //�Q�j�@@���ʃR�[�h�`�F�b�N 
            //this.���ʃR�[�h ==null or  
            //this.���ʃR�[�h.length==0�̏ꍇ�A��O��throw����B 
            if (requestCode == null || requestCode.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʃR�[�h�����w��ł��B"); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690271
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXSingleSignOnResponse(this);
    }
}@
