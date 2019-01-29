head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҊO�������������ꗗ���N�G�X�g(WEB3AdminFeqProductListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
*/


package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������������ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҊO�������������ꗗ���N�G�X�g�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqProductListRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqProductListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ���j���[��ʂ���Ăяo���ꂽ�ꍇ�́Anull
     */
    public String marketCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �����������\
     */
    public String productName;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[�I�u�W�F�N�g�̔z��
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE39FB00FA
     */
    public WEB3AdminFeqProductListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�P�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00090<BR>
     * �@@�P�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �Q�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00091<BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00092<BR>
     * �@@�Q�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * �R�j�@@�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�R�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00231<BR>
     * �@@�R�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00232<BR>
     * �@@�R�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�R�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�R�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     *  �@@�@@��O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�@@ �O��������������.�����R�[�h <BR>
     * �@@�@@�@@�@@ �O��������������.���n�����R�[�h <BR>
     *          �O��������������.������<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 42972D270290
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
                
        //�P�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł�:" + this.pageIndex); 
        }
        
        //�P�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B        
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł�:" + this.pageIndex); 
        }
        
        //�Q�j�@@�y�[�W���\���s���`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B"); 
        }
        
        //�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł�:" + this.pageSize); 
        }
        
        //�Q�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł�:" + this.pageSize); 
        } 
        
        //�R�j�@@�@@�\�[�g�L�[�̃`�F�b�N
        //�R�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�����w��ł��B"); 
        }
        
        //�R�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�̗v�f�����O�ł��B"); 
        }
        
        //�R�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_intCnt = this.sortKeys.length;
        for(int i = 0; i < l_intCnt; i++)
        {
            WEB3ForeignSortKey l_key = this.sortKeys[i];
            
            if (l_key != null)
            {
                //�R�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
                l_key.validate();
                
                //�R�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A
                //��O���X���[����B
                //�O��������������.�����R�[�h
                //�O��������������.���n�����R�[�h 
                if (!(WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_key.keyItem) || 
                    WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(l_key.keyItem)))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł�:" + l_key.keyItem); 
                }
            }
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
        return new WEB3AdminFeqProductListResponse(this);
    }
}
@