head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������������`�[�ꗗ���N�G�X�g(WEB3AdminFeqOrderVoucherListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO���������������`�[�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҊO���������������`�[�ꗗ���N�G�X�g�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�����ꗗ)<BR>
     * �O���������������`�[�ꗗ�����̔z��
     */
    public WEB3AdminFeqOrderVoucherListCondUnit[] condList;
    
    /**
     * @@roseuid 42CE39FB0251
     */
    public WEB3AdminFeqOrderVoucherListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�����ꗗ<BR>
     * <BR>
     * �P�|�P�j<BR>
     *    this.�����ꗗ == null or<BR>
     *    this.�����ꗗ.length() == 0<BR>
     * <BR>
     *    �̏ꍇ�A�u�s�ꖢ�I���v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02045<BR>
     * 
     * <BR>
     * �P�|�Q�j�e�v�f���Ɉȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�|�Q�|�P�j<BR>
     *    ����.�s��R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A�u�s��R�[�h���ݒ�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02046<BR>
     * <BR>
     * �P�|�Q�|�Q�j<BR>
     *    �i����.�������i���j != null and ����.�������i���j == null �j or<BR>
     *    �i����.�������i���j == null and ����.�������i���j != null �j or<BR>
     *    �i����.�������i���j != null and ����.�������i���j != null and <BR> 
     *      ����.�������i���j > ����.�������i���j �j<BR> 
     * <BR>
     *    �̏ꍇ�A�u�����������ݒ�G���[�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02047<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A0176803E7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����ꗗ
        //this.�����ꗗ == null or this.�����ꗗ.length() == 0
        //�̏ꍇ�A�u�s�ꖢ�I���v�̗�O���X���[����B
        if (this.condList == null || this.condList.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " �s�ꖢ�I���G���[�B" ); 
        }
        
        //�P�|�Q�j�e�v�f���Ɉȉ��̃`�F�b�N���s���B
        int l_intCnt = this.condList.length;
        for (int i =0; i < l_intCnt; i ++)
        {
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = this.condList[i];
            if (l_unit != null)
            {
                //�P�|�Q�|�P�j����.�s��R�[�h == null�̏ꍇ�A�u�s��R�[�h���ݒ�v�̗�O���X���[����B
                if (WEB3StringTypeUtility.isEmpty(l_unit.marketCode))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02046,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �s��R�[�h���ݒ�G���[�B" ); 
                }
                //�P�|�Q�|�Q�j�i����.�������i���j != null and ����.�������i���j == null �j or
                //�i����.�������i���j == null and ����.�������i���j != null �j or
                //�i����.�������i���j != null and ����.�������i���j != null and
                //����.�������i���j > ����.�������i���j �j
                // �̏ꍇ�A�u�����������ݒ�G���[�v�̗�O���X���[����B
                if ((l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo == null) ||
                    (l_unit.orderBizDateFrom == null && l_unit.orderBizDateTo != null) ||
                    (l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo != null &&                         
                        WEB3DateUtility.compareToDay(l_unit.orderBizDateFrom, l_unit.orderBizDateTo) > 0))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02047,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �����������ݒ�G���[�B" + 
                        l_unit.orderBizDateFrom + "(����.�������i���j)" + 
                        l_unit.orderBizDateTo + "(����.�������i���j)"); 
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
        return new WEB3AdminFeqOrderVoucherListResponse(this);
    }
}
@
