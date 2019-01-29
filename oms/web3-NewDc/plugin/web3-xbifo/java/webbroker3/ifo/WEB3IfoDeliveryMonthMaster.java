head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDeliveryMonthMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����}�X�^(WEB3IfoDeliveryMonthMaster.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/24 ���z(���u) �V�K�쐬 ���f��888, 892, 893
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.data.IfoDeliveryMonthMasterDao;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨OP�����}�X�^)<BR>
 * �敨OP�����}�X�^�N���X<BR>
 * <BR>
 * �iDB���C�A�E�g �u�敨OP�����}�X�^�e�[�u��.xls�v�Q�Ɓj<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3IfoDeliveryMonthMaster
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDeliveryMonthMaster.class);

    /**
     * (�敨OP�����}�X�^Row)<BR>
     * �敨OP�����}�X�^�s�I�u�W�F�N�g<BR>
     * �i��������DAO�N���X�j<BR>
     */
    private IfoDeliveryMonthMasterRow ifoDeliveryMonthMasterRow;

    /**
     * this.�敨OP�����}�X�^Row��ԋp����B<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.ifoDeliveryMonthMasterRow;
    }

    /**
     * (�敨OP�����}�X�^)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̌����Y�����R�[�h�A�敨/�I�v�V�����敪�A�����Ɉ�v����<BR>
     * �敨OP�����}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂĐ敨OP�����}�X�^�e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�敨OP�����}�X�^Row�j��<BR>
     * this.�敨OP�����}�X�^Row�ɃZ�b�g����B<BR>
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h<BR>
     * @@param l_strFuturesOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@param l_strMonthOfDelivery - (����)<BR>
     * ����<BR>
     * @@throws WEB3BaseException
     */
    public WEB3IfoDeliveryMonthMaster(
        String l_strUnderlyingProductCode, String l_strFuturesOptionDiv, String l_strMonthOfDelivery)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3IfoDeliveryMonthMaster(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            this.ifoDeliveryMonthMasterRow =
                IfoDeliveryMonthMasterDao.findRowByPk(
                    l_strUnderlyingProductCode,
                    l_strFuturesOptionDiv,
                    l_strMonthOfDelivery);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}@
