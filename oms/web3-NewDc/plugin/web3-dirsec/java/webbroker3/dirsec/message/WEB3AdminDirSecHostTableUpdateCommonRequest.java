head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u���X�V���ʃ��N�G�X�g(WEB3AdminDirSecHostTableUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�L���[�e�[�u���X�V���ʃ��N�G�X�g)<BR>
 * �L���[�e�[�u���X�V���ʃ��N�G�X�g�N���X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUpdateCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableUpdateCommonRequest.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (�e�[�u����)<BR>
     * �e�[�u�����i�a���j�B
     */
    public String tableJpnName;
    
    /**
     * (tableName)<BR>
     * �e�[�u���������B
     */
    public String tableName;
    
    /**
     * @@roseuid 442A1C8803D8
     */
    public WEB3AdminDirSecHostTableUpdateCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�e�[�u�����`�F�b�N <BR>
     * �@@this.�e�[�u���� == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�e�[�u������null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02424<BR>
     * <BR>
     * �Q�j�e�[�u��������<BR>
     * �@@this.�e�[�u�������� == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�e�[�u����������null�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02425<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4416502B0168
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�e�[�u�����`�F�b�N 
        //�@@this.�e�[�u���� == null�̏ꍇ�A 
        //�@@�@@�@@�@@�@@�u�e�[�u������null�v�̗�O���X���[����B 
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_02424
        if (this.tableJpnName == null)
        {
            log.debug("�e�[�u������null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02424,
                this.getClass().getName() + STR_METHOD_NAME,
                "�e�[�u������null�ł��B");
        }
        
        //�Q�j�e�[�u��������
        //�@@this.�e�[�u�������� == null�̏ꍇ�A 
        //�@@�@@�@@�@@�@@�u�e�[�u����������null�v�̗�O���X���[����B
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_02425
        if (this.tableName == null)
        {
            log.debug("�e�[�u����������null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02425,
                this.getClass().getName() + STR_METHOD_NAME,
                "�e�[�u����������null�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminDirSecHostTableUpdateCommonResponse(this);
    }
}
@
