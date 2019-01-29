head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccInformationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�������(WEB3FXAccInformationUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
Revesion History : 2008/09/26 ���g (���u) �d�l�ύX�E���f��No.1017,1070
Revesion History : 2009/08/25 �И��� (���u) �d�l�ύX�E���f��No.1191
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�������) <BR>
 * FX������� <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccInformationUnit extends Message
{
    /**
     * (�R�[�X�敪) <BR>
     * �ב֕ۏ؋�����R�[�X�敪 <BR>
     * <BR>
     * 0�FDEFAULT <BR>
     * 1�F1���ʉ݃R�[�X <BR>
     * 2�F10���ʉ݃R�[�X <BR>
     * 3�FCFD�R�[�X<BR>
     */
    public String fxCourseDiv;

    /**
     * (�����ԍ�) <BR>
     * �ב֕ۏ؋������ԍ� <BR>
     */
    public String fxAccountCode;

    /**
     * (FX�������) <BR>
     * �R���X�g���N�^�B <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit
     * @@roseuid 41B0393C0146
     */
    public WEB3FXAccInformationUnit()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccInformationUnit.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j �R�[�X�敪�`�F�b�N <BR>
     * this.�R�[�X�敪��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01792 <BR>
     * <BR>
     * this.�R�[�X�敪�����L�̍��ڈȊO�̏ꍇ�A��O��throw����B <BR>
     * �E�h0�FDEFAULT�h <BR>
     * �E�h1�F1���ʉ݃R�[�X�h <BR>
     * �E�h2�F10���ʉ݃R�[�X�h <BR>
     * �E�h3�FCFD�R�[�X�h<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01793 <BR>
     * <BR>
     * �Q�j �����ԍ��`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * �Q�|�P�j this.�����ԍ���null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01794 <BR>
     * <BR>
     * �Q�|�Q�j this.�����ԍ������� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01105 <BR>
     * <BR>
     * �Q�|�S�j�@@this.�����ԍ���10�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01106 <BR>
     * <BR>
     * 
     * @@roseuid 41BD529E038A
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j �R�[�X�敪�`�F�b�N 
        // this.�R�[�X�敪��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01792 
        if (WEB3StringTypeUtility.isEmpty(this.fxCourseDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01792,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�R�[�X�敪 = null"); 
        }

        // this.�R�[�X�敪�����L�̍��ڈȊO�̏ꍇ�A��O��throw����B 
        // �E�h0�FDEFAULT�h 
        // �E�h1�F1���ʉ݃R�[�X�h 
        // �E�h2�F10���ʉ݃R�[�X�h  
        //�E�h3�FCFD�R�[�X�h
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01793 
        if (!(WEB3GftTransStatusCourseDivDef.DEFAULT.equals(this.fxCourseDiv) ||
            WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(this.fxCourseDiv) ||
            WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(this.fxCourseDiv)
            || WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(this.fxCourseDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01793,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�R�[�X�敪�����L�̍��� : " +
                "[0�FDEFAULT" +
                "1�F1���ʉ݃R�[�X" + 
                "2�F10���ʉ݃R�[�X" +
                "3�FCFD�R�[�X]" + "�ȊO" +
                "���N�G�X�g�f�[�^.�R�[�X�敪 = " + this.fxCourseDiv); 
        }
 
        // �Q�j �����ԍ��`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B 
        // �Q�|�P�j this.�����ԍ���null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01794
        if (WEB3StringTypeUtility.isEmpty(this.fxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01794,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����ԍ� = null"); 
        }
 
        // �Q�|�Q�j this.�����ԍ������� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01105 
        if (!WEB3StringTypeUtility.isNumber(this.fxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01105,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����ԍ�������" + 
                "���N�G�X�g�f�[�^.�����ԍ� = " + this.fxAccountCode); 
        }
 
        // �Q�|�S�j�@@this.�����ԍ���10�� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01106
        if (this.fxAccountCode.length() > 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01106,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����ԍ���10��" + 
                "���N�G�X�g�f�[�^.�����ԍ� = " + this.fxAccountCode); 
        }
        
        log.exiting(l_strMethodName);
    }
}@
