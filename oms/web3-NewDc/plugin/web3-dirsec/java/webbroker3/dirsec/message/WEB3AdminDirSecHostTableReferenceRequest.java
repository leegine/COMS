head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�N���X(WEB3AdminDirSecHostTableReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�N���X�B<BR>
 *
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableReferenceRequest extends WEB3GenRequest 
{   
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s���B<BR>
     */
    public String pageSize;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;
    
    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C86009C
     */
    public WEB3AdminDirSecHostTableReferenceRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�y�[�W���\���s���`�F�b�N<BR> 
     * �@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR> 
     * <BR>
     * �@@�P�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>  
     * <BR>
     * �@@�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     * <BR>
     * �Q�j�\���ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR> 
     * <BR>
     * �@@�Q�|�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>  
     * <BR>
     * �@@�Q�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>  
     * <BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�`�F�b�N<BR> 
     * �@@�R�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR> 
     * <BR>
     * �@@�R�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR> 
     * <BR>
     * �@@�R�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂��� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B <BR>
     * �@@�@@�R�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44164B0F0020
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�y�[�W���\���s���`�F�b�N  
        //�@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B 
        if (this.pageSize == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���̓��͂��s���ł��B");     
        }
        
        //�@@�P�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A  
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���������ȊO�̒l�ł��B");     
        }
        
        //�@@�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A  
        //�@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���̒l��0�ȉ��ł��B");     
        }
        
        //�Q�j�\���ԍ��`�F�b�N 
        //�@@�Q�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B
        if (this.pageIndex == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog. BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ������w��ł��B");     
        }
        
        //�@@�Q�|�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A  
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B  
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");     
        }
        
        //�@@�Q�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A  
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B  
        if (Integer.parseInt(this.pageIndex) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");     
        }
        
        //�R�j�@@�\�[�g�L�[�`�F�b�N  
        //�@@�R�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�����w��ł��B");     
        }
        
        //�@@�R�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B 
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�̗v�f�����O�ł�");     
        }
        
        //�@@�R�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂���  
        //�@@�@@�@@�@@���L�̃`�F�b�N���s���B  
        //�@@�@@�R�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B
        for (int i = 0 ; i < l_intsortKeysLen ; i++) 
        {
            sortKeys[i].validate();    
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
        return new WEB3AdminDirSecHostTableReferenceResponse(this);
    }
}
@
