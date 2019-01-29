head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g(WEB3AdminPvInfoConditionListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ������(���u) �쐬
Revesion History : 2004/11/2  鰊](���u) �C��
Revesion History : 2005/12/8 杊��](���u) �d�l�ύXNo.059�C��
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�������ԍ�)<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     */
    public String conditionNumber;
    
    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * �������͂̏ꍇ�́APR�w�̃Z�b�V�����ŕێ����Ă���<BR>
     * �@@�Ǘ��Ҏ戵���X�R�[�h���Z�b�g�B<BR>
     */
    public String[] branchCodeList;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�ŁA<BR>
     * �@@�ȉ��̏����̂��Âꂩ�ɊY������ꍇ�́A<BR>
     * �@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�ڋq�R�[�h.length != 6��<BR>
     * �@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00780<BR>
     * 
     * �Q�j���X�R�[�h�̃`�F�b�N <BR>
     *  this.���X�R�[�h != null�̏ꍇ�ŁA<BR> 
     *  this.���X�R�[�h�̊e�v�f�� <BR>
     *  �ȉ��̏����̂��Âꂩ�ɊY������ꍇ�́A <BR>
     *  �u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     *      �Ethis.���X�R�[�h != ���l <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01729<BR>
     *      �Ethis.���X�R�[�h.length != 3�� <BR>
     *   class :  WEB3BusinessLayerException <BR>
     *   tag :   �@@BUSINESS_ERROR_00834       
     * <BR> 
     * �R�j�v���y�[�W�ԍ��`�F�b�N<BR>
     *�@@�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     *�@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     *�@@�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�y�[�W���\���s���`�F�b�N<BR>
     *�@@�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     *�@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     *�@@�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     *�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00617<BR>
     * @@roseuid 415BEA4400F6
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�ڋq�R�[�h�̃`�F�b�N
        if (this.accountCode != null)
        {
            int l_intAcountCode = WEB3StringTypeUtility.getByteLength(this.accountCode);
            boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(this.accountCode);
            if(l_intAcountCode != 6 || !l_blnIsNumber)
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00780.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }
        //�Q�j���X�R�[�h�̃`�F�b�N
        if (branchCodeList != null)
        {
            for (int i = 0; i < branchCodeList.length; i++)
            {
                if (!WEB3StringTypeUtility.isNumber(this.branchCodeList[i]))
                {
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        getClass().getName() + "." + STR_METHOD_NAME); 
                }
                
                if (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != 3)
                {
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + "." + STR_METHOD_NAME); 
                }
            }
        }
        
        //�R�j�v���y�[�W�ԍ��`�F�b�N
        //�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ
        if (null == this.pageIndex)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�S�j�y�[�W���\���s���`�F�b�N
        //�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ
        if (null == this.pageSize)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
        //�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327C0000F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionListResponse(this);
    }
}
@
