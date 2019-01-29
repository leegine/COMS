head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���v���_�N�g�}�l�[�W��(WEB3BondProductManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
                    2006/10/08 ���� (������c�Ɠ��ł��u) �d�l�ύX�E���f��107�A115
                    2006/10/10 �ęԍg (���u) �d�l�ύX�E���f��121
                    2006/10/16 ��іQ (���u) �d�l�ύX�E���f��124�A125�A129
 Revesion History : 2007/07/11 ���n�m (���u) �d�l�ύX�E���f��195,198,202
 Revesion History : 2007/07/25 �Ӑ� (���u) �d�l�ύX�E���f��218
 Revesion History : 2007/08/08 �Ӑ� (���u) �d�l�ύX�E���f��251
 Revesion History : 2007/09/10 ���n�m (���u) �d�l�ύX�E���f��256,���f��257
 Revesion History : 2007/10/08 ���g (���u) �d�l�ύX�E���f��258
 Revesion History : 2008/08/13 �g�C�� (���u) �d�l�ύX�E���f��260
 Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�E���f��262,263 �c�a�X�V�d�lNo.044
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondProduct;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductManagerImpl;

import com.fitechlabs.dbind.Row;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3OnlineDispDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.data.BondAutoExecLimitActionRow;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.data.BondIssueCouponTypeRow;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondProductSortKeyItemDef;
import webbroker3.bd.message.WEB3AdminBondProductUpdateInfo;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;

/**
 * (���v���_�N�g�}�l�[�W��)<BR>
 * ���v���_�N�g�}�l�[�W���N���X�B <BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondProductManager extends BondProductManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondProductManager.class);

    /**
     * @@roseuid 44E3361B005D
     */
    public WEB3BondProductManager()
    {

    }

    /**
     * (get������)<BR>
     * �������I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �P�j�ȉ��̏����ō������e�[�u������f�[�^���擾����B <BR>
     * <BR>
     * [����] <BR>
     * ����ID�F ����.����ID <BR>
     * <BR>
     * �Q�j�擾�����f�[�^����������I�u�W�F�N�g�𐶐����A�ԋp����B <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@return BondProduct
     * @@throws WEB3BaseException
     * @@roseuid 42195ECE00B5
     */
    public BondProduct getBondProduct(long l_lngProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondProduct(long)";
        log.entering(STR_METHOD_NAME);

        BondProduct l_bondProduct = null;

        try
        {
            l_bondProduct = (BondProduct) this.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bondProduct;
    }

    /**
     * (to����)<BR>
     * �itoProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����.�s�I�u�W�F�N�g�����Ƃɍ������I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * @@param l_row - �s�I�u�W�F�N�g<BR>
     * @@return Product
     * @@roseuid 42195F430209
     */
    public Product toProduct(Row l_row)
    {
        final String STR_METHOD_NAME = "toProduct(Row)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_row instanceof ProductRow)
            {
                log.exiting(STR_METHOD_NAME);
                return new WEB3BondProduct((ProductRow)l_row);
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return new WEB3BondProduct((BondProductRow)l_row);
            }
        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating WEB3BondProduct for product id: " +
                ((ProductRow)l_row).getProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }
    }

    /**
     * (validate�������e)<BR>
     * �ivalidate�������e()�j <BR>
     * �������X�V���̓o�^�R�����s���B <BR>
     * <BR>
     * �P�jget������()���\�b�h�ŁA���������擾����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЁF�،���ЃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�����R�[�h�iWEB3�j�@@�F�������X�V���.�����R�[�h(WEB3)<BR>
     * <BR>
     * �Q�j�������X�V���́u�戵�J�n�����v�u�戵�I�������v���`�F�b�N�B<BR>
     * �@@�@@�Q�|�P�j�������X�V���.�戵�J�n���� != null�@@���́@@<BR>
     * �@@�@@�@@�@@�@@�@@�������X�V���.�戵�I������ != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�戵�J�n����(*1)�@@���@@�戵�I������(*2)�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�w�戵�J�n�����Ǝ戵�I�������̊֌W���s���ł��B�x<BR>
     *�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02621<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*1)�ȉ��̒l���g�p����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�J�n���� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@  �@@�@@�@@�@@�������X�V���.�戵�J�n�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�J�n���� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.�戵�J�n�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*2)�ȉ��̒l���g�p����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�I������ != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�I�������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�I������ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.�戵�I�������B<BR>
     * (*1)�̒l != null ���� (*2)�̒l != null�̏ꍇ�ɔ�r������B <BR>
     * <BR>
     * <BR>
     * �R�j�������X�V���́u����J�n���v�A�u����I�����v���`�F�b�N<BR>
     * �@@�@@�R�|�P�j�������X�V���.����J�n�� != null�@@���́@@�������X�V���.����I���� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@����J�n��(*1)�@@���@@����I����(*2)�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�w����J�n���Ɖ���I�����̊֌W���s���ł��B�x<BR>
     *�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02622<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*1)�������X�V���.����J�n�� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.����J�n���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������X�V���.����J�n�� == null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������.����J�n���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*2)�������X�V���.����I���� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.����I�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������X�V���.�戵�I������ == null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������.����I�����B<BR>
     * <BR>
     * (*1)�̒l != null ���� (*2)�̒l != null�̏ꍇ�ɔ�r������B<BR>
     * <BR>
     * �@@�@@�R�|�Q�j������.���^�C�v == �������̏ꍇ�A<BR>
     * �@@�@@�@@�R�|�Q�|�P�j������.����J�n��(SONAR) != null ���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.����J�n�� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.����J�n��(SONAR) ���������X�V���.����J�n���ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[�w����J�n��(SONAR)�Ɖ���J�n���̊֌W���s���ł��B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02623<BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�|�Q�j������.����I����(SONAR) != null ���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.����I���� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.����I���� �� ������.����I����(SONAR)�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[�w����I����(SONAR)�Ɖ���I�����̊֌W���s���ł��B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02624<BR>
     * <BR>
     * �S�j�������X�V���̔��t�P�����`�F�b�N <BR>
     * �@@�S�|�P�j�g���������}�l�[�W��.validate�P�����Ă� <BR>
     * �@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�������F�擾���������� <BR>
     * �@@�@@�@@�@@�@@�P���@@�@@�@@�F�������X�V���.���t�P�� <BR>
     * <BR>
     * �T�j�������X�V���̔��p�P�����`�F�b�N <BR>
     * �@@�T�|�P�j�g���������}�l�[�W��.validate�P�����Ă� <BR>
     * �@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�������F�擾���������� <BR>
     * �@@�@@�@@�@@�@@�P���@@�@@�@@�F�������X�V���.���p�P�� <BR>
     * <BR>
     * �U�j�������X�V���̍ō��z�ʂƍŒ�z�ʂ��`�F�b�N<BR>
     * �@@  �U�|�P�j�������X�V���.�Œ�z�� != null�@@���́@@�������X�V���.�ō��z�� != null�̏ꍇ�A  <BR>
     * �@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B <BR>
     * �@@�@@�@@�@@�U�|�P�|�P�j�Œ�z��(*1)�@@���@@�ō��z��(*2)�ł���ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[�w�ō��z�ʂƍŒ�z�ʂ̊֌W���s���ł��B�x  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02625<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@(*1)�������X�V���.�Œ�z�� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�Œ�z�ʁB  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�Œ�z�� == null�̏ꍇ   <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������.�Œ�z�ʁB <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@(*2)�������X�V���.�ō��z�� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�ō��z�ʁB   <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������X�V���.�ō��z�� == null�̏ꍇ   <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������.�ō��z�ʁB   <BR>
     * (*1)�̒l != null ���� (*2)�̒l != null�̏ꍇ�ɔ�r������B <BR>
     * <BR>
     * �V�j���助�U�`���E������󂯋敪�`�F�b�N<BR>
     * �@@�@@�V�|�P�j�������X�V���.�����敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�V�|�P�|�P�j�������X�V���.���助�U�`�� == null �܂��́A<BR>
     * �@@�@@�@@�@@�@@�@@�������X�V���.������󂯋敪 == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�w��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B�x<BR>
     * �@@�@@�@@�@@�@@�@@���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03106<BR>
     * <BR>
     * �@@�@@�V�|�Q�j�������X�V���.�����敪 �� �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�V�|�Q�|�P�j�������X�V���.���助�U�`�� �� null �܂��́A<BR>
     * �@@�@@�@@�@@�@@�@@�������X�V���.������󂯋敪 �� null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�w��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B�x<BR>
     * �@@�@@�@@�@@�@@�@@���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03107<BR>
     * <BR>
     * �W�j��n���`�F�b�N<BR>
     * �@@�@@�W�|�P�j�������X�V��� .��n�� != null�@@����<BR>
     * �@@�@@�@@�@@�������X�V��� .��n�� !=������. ��n���̏ꍇ�A<BR>
     * �@@�@@�@@�@@�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * �@@�@@�@@�@@�W�|�P�|�P�j �������X�V��� .��n������c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u��n������c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02336<BR>
     * <BR>
     * �@@�@@�@@�@@�W�|�P�|�Q�j�������X�V��� .��n�������ݓ��t�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u��n�����s���ł��B�v���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02865<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_productUpdateInfo - (�������X�V���)<BR>
     * �������X�V���<BR>
     * @@param l_strProductCode - (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)<BR>
     * @@roseuid 44C44AF10311
     */
    public void validateProductSpec(Institution l_institution,
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo,
        String l_strProductCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateProductSpec(Institution, WEB3AdminBondProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_productUpdateInfo == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTradeStartDate = null ;
        Date l_datTradeEndDate = null ;
        Date l_datRecruitStartDate = null ;
        Date l_datRecruitEndDate = null ;

        // �P�jget������()���\�b�h�ŁA���������擾����B
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct) this.getBondProduct
                (l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�������X�V���́u�戵�J�n�����v�u�戵�I�������v���`�F�b�N�B
        //�Q�|�P�j�������X�V���.�戵�J�n���� != null�@@���́@@
        //�������X�V���.�戵�I������ != null�̏ꍇ�A
        //�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
        if ((l_productUpdateInfo.tradeStartDate != null) || (l_productUpdateInfo.tradeEndDate != null))
        {
            //(*1)�ȉ��̒l���g�p����B
            //�������X�V���.�戵�J�n���� != null�̏ꍇ�A
            //�������X�V���.�戵�J�n����
            //�������X�V���.�戵�J�n���� == null�̏ꍇ�A
            //������.�戵�J�n�����B
            if (l_productUpdateInfo.tradeStartDate != null)
            {
                l_datTradeStartDate = l_productUpdateInfo.tradeStartDate;
            }
            else
            {
                l_datTradeStartDate = l_bondProduct.getTradeStartDate();
            }

            //(*2)�ȉ��̒l���g�p����B
            //�������X�V���.�戵�I������ != null�̏ꍇ�A
            //�������X�V���.�戵�I�������B
            //�������X�V���.�戵�I������ == null�̏ꍇ�A
            //������.�戵�I�������B
            if (l_productUpdateInfo.tradeEndDate != null)
            {
                l_datTradeEndDate = l_productUpdateInfo.tradeEndDate;
            }
            else
            {
                l_datTradeEndDate = l_bondProduct.getTradeEndDate();
            }

            if (l_datTradeStartDate != null && l_datTradeEndDate != null)
            {
                //�戵�J�n����(*1)�@@���@@�戵�I������(*2)�ł���ꍇ�A
                //�G���[�w�戵�J�n�����Ǝ戵�I�������̊֌W���s���ł��B�x
                if (l_datTradeStartDate.compareTo(l_datTradeEndDate) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02621,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�戵�J�n�������戵�I�������𒴂��Ă��܂��B");
                }
            }

        }
        //�R�j�������X�V���́u����J�n���v�A�u����I�����v���`�F�b�N
        //�R�|�P�j�������X�V���.����J�n�� != null�@@���́@@�������X�V���.����I���� != null�̏ꍇ�A
        //�ȉ��̃`�F�b�N���s�Ȃ��B
        if ((l_productUpdateInfo.recruitStartDate != null) || (l_productUpdateInfo.recruitEndDate != null))
        {
            //(*1)�������X�V���.����J�n�� != null�̏ꍇ�A
            //�������X�V���.����J�n���B
            //�������X�V���.����J�n�� == null�̏ꍇ
            //������.����J�n���B
            if (l_productUpdateInfo.recruitStartDate != null)
            {
                l_datRecruitStartDate = l_productUpdateInfo.recruitStartDate;
            }
            else
            {
                l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
            }

            //(*2)�������X�V���.����I���� != null�̏ꍇ�A
            //�������X�V���.����I�����B
            //�������X�V���.�戵�I������ == null�̏ꍇ
            //������.����I�����B
            if (l_productUpdateInfo.recruitEndDate != null)
            {
                l_datRecruitEndDate = l_productUpdateInfo.recruitEndDate;
            }
            else
            {
                l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
            }

            //����J�n��(*1)�@@���@@����I����(*2)�ł���ꍇ�A
            //�G���[�w����J�n���Ɖ���I�����̊֌W���s���ł��B�x
            if (l_datRecruitStartDate != null && l_datRecruitEndDate != null)
            {
                if (l_datRecruitStartDate.compareTo(l_datRecruitEndDate) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02622,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "����J�n��������I�����𒴂��Ă��܂��B");
                }
            }

            //�R�|�Q�j������.���^�C�v == �������̏ꍇ�A
            if (BondTypeEnum.DOMESTIC_BOND == l_bondProduct.getBondType())
            {
                //�R�|�Q�|�P�j������.����J�n��(SONAR) != null ���A
                //�������X�V���.����J�n�� != null�̏ꍇ�A
                //�ȉ��̃`�F�b�N���s�Ȃ�
                if (l_bondProduct.getHostRecruitStartDate() != null &&
                    l_productUpdateInfo.recruitStartDate != null)
                {

                    // ������.����J�n��(SONAR) ���������X�V���.����J�n���ł���ꍇ�A
                    // �G���[�w����J�n��(SONAR)�Ɖ���J�n���̊֌W���s���ł��B�x
                    if (l_bondProduct.getHostRecruitStartDate().compareTo(
                        l_productUpdateInfo.recruitStartDate) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02623,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "����J�n��������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B");
                    }
                }

                //�R�|�Q�|�Q�j������.����I����(SONAR) != null ���A
                //�������X�V���.����I���� != null�̏ꍇ�A
                //�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
                if (l_bondProduct.getHostRecruitEndDate() != null &&
                    l_productUpdateInfo.recruitEndDate != null)
                {
                     //�������X�V���.����I���� �� ������.����I����(SONAR)�ł���ꍇ�A
                     //�G���[�w����I����(SONAR)�Ɖ���I�����̊֌W���s���ł��B�x
                    if (l_productUpdateInfo.recruitEndDate.compareTo(
                        l_bondProduct.getHostRecruitEndDate()) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02624,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "����I����������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B");
                    }
                  }
            }
        }
        // �S�j�������X�V���̔��t�P�����`�F�b�N 
        // �S�|�P�j�g���������}�l�[�W��.validate�P�����Ă� 
        //  [����] 
        //  �������F�擾���������� 
        //  �P���@@�@@�@@�F�������X�V���.���t�P��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_productUpdateInfo.buyPrice);
      
        //�T�j�������X�V���̔��p�P�����`�F�b�N 
        //�T�|�P�j�g���������}�l�[�W��.validate�P�����Ă� 
        // [����] 
        // �������F�擾���������� 
        // �P���@@�@@�@@�F�������X�V���.���p�P�� 
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_productUpdateInfo.sellPrice);
        
        // �U�j�������X�V���̍ō��z�ʂƍŒ�z�ʂ��`�F�b�N
        //�U�|1�j�������X�V���.�Œ�z�� != null�@@���́@@�������X�V���.�ō��z�� != null�̏ꍇ�A
        //        �ȉ��̃`�F�b�N���s�Ȃ��B
        if (l_productUpdateInfo.minFaceAmount != null || l_productUpdateInfo.maxFaceAmount != null)
        {
            String l_strMinFaceAmount = null;
            String l_strMaxFaceAmount = null;

            //(*1)�������X�V���.�Œ�z�� != null�̏ꍇ�A
            //�������X�V���.�Œ�z�ʁB
            //�������X�V���.�Œ�z�� == null�̏ꍇ
            //������.�Œ�z�ʁB
            if (l_productUpdateInfo.minFaceAmount != null)
            {
                l_strMinFaceAmount = l_productUpdateInfo.minFaceAmount;
            }
            else
            {
                l_strMinFaceAmount =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());
            }

            //(*2)�������X�V���.�ō��z�� != null�̏ꍇ�A
            //�������X�V���.�ō��z�ʁB
            //�������X�V���.�ō��z�� == null�̏ꍇ
            //������.�ō��z��
            if (l_productUpdateInfo.maxFaceAmount != null)
            {
                l_strMaxFaceAmount = l_productUpdateInfo.maxFaceAmount;
            }
            else
            {
                l_strMaxFaceAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProduct.getMaxFaceAmount());
            }

            //  �U�|1�|�P�j�Œ�z��(*1)�@@���@@�ō��z��(*2)�ł���ꍇ�A
            //�@@�@@�@@�@@�@@�G���[�w�ō��z�ʂƍŒ�z�ʂ̊֌W���s���ł��B�x
            if (l_strMinFaceAmount != null && l_strMaxFaceAmount != null)
            {
                BigDecimal l_bdMinFaceAmount = new BigDecimal(l_strMinFaceAmount);
                BigDecimal l_bdMaxFaceAmount = new BigDecimal(l_strMaxFaceAmount);
                if(l_bdMinFaceAmount.compareTo(l_bdMaxFaceAmount) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02625,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�Œ�z�ʂ��ō��z�ʂ𒴂��Ă��܂��B");
                }
            }
        }

        //�V�j���助�U�`���E������󂯋敪�`�F�b�N
        //�V�|�P�j�������X�V���.�����敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        if (WEB3BondTradeTypeDef.RECRUIT.equals(l_productUpdateInfo.buySellDiv))
        {
            //�V�|�P�|�P�j�������X�V���.���助�U�`�� == null �܂��́A�������X�V���.������󂯋敪 == null �̏ꍇ�A 
            //�G���[�w��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B�x���X���[����B
            if (l_productUpdateInfo.recruitInvitationForm == null || l_productUpdateInfo.recruitAcceptDiv == null)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03106,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B");
            }
        }
        //�V�|�Q�j�������X�V���.�����敪 �� �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        else
        {
            //�V�|�Q�|�P�j�������X�V���.���助�U�`�� �� null �܂��́A�������X�V���.������󂯋敪 �� null �̏ꍇ�A 
            //�G���[�w��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B�x���X���[����B
            if (l_productUpdateInfo.recruitInvitationForm != null || l_productUpdateInfo.recruitAcceptDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03107,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B");
            }
        }

        //�W�j��n���`�F�b�N
        //�W�|�P�j�������X�V��� .��n�� != null�@@����
        //�������X�V��� .��n�� !=������. ��n���̏ꍇ�A
        //�ȉ��̃`�F�b�N���s�Ȃ��B
        if (l_productUpdateInfo.deliveryDate != null
            && (WEB3DateUtility.compareToDay(
                l_productUpdateInfo.deliveryDate, l_bondProduct.getDeliveryDate()) != 0))
        {
            //�W�|�P�|�P�j �������X�V��� .��n������c�Ɠ��̏ꍇ�A
            //�G���[�u��n������c�Ɠ��ł��B�v���X���[����B
            Timestamp l_tsDeliveryDate =
                new Timestamp(l_productUpdateInfo.deliveryDate.getTime());
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDeliveryDate)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("��n������c�Ɠ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n������c�Ɠ��ł��B");
            }

            //�W�|�P�|�Q�j�������X�V��� .��n�������ݓ��t�̏ꍇ�A
            //�G���[�u��n�����s���ł��B�v���X���[����B
            if (WEB3DateUtility.compareToDay(
                l_productUpdateInfo.deliveryDate,
                GtlUtils.getSystemTimestamp()) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("��n�����s���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n�����s���ł��B");
            }
            
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get������)<BR>
     * ���������擾����B <BR>
     * <BR>
     * �P�j�@@this.get������(Institution, String, String)���R�[������B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@Institution�F����.�،����<BR>
     * �@@�@@�@@String�F����.�����R�[�h(WEB3) <BR>
     * �@@�@@�@@String�F"0"<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����������I�u�W�F�N�g��Ԃ��B <BR>
     * @@param l_institution - (�،����)<BR>
     * @@param l_strProductCode - (�����R�[�h(WEB3))<BR>
     * @@return BondProduct
     * @@throws NotFoundException
     * @@roseuid 44C44AF103CC
     */
    public BondProduct getBondProduct(
        Institution l_institution,
        String l_strProductCode) throws NotFoundException
    {
        return this.getBondProduct(l_institution, l_strProductCode, "0");
    }

    /**
     * (get���������g�����ꗗ)<BR>
     * ���������g�����ꗗ��Ԃ��B <BR>
     * <BR>
     * �P�j�@@���������g�����e�[�u�����������A�������ʂ�List���擾����B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@����ID = ����.������.����ID<BR>
     * �@@�@@�@@�I�����C���\���敪 = �L��<BR>
     * �@@�@@�m�\�[�g�����n <BR>
     * �@@�@@�@@���g�X�V���t�@@�~��<BR>
     * <BR>
     * �Q�j�@@List��ԋp����B<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF20013
     */
    public List getBondAutoExecLimitHistoryList(BondProduct l_bondProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondAutoExecLimitHistoryList(BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID = ����.������.����ID , �I�����C���\���敪 = �L��
        String l_strQueryCondition = " product_id = ? and online_disp_div = ? ";

        //���g�X�V���t�@@�~��
        String l_strOrderBy = " execution_update_date desc ";
        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = new Long(l_bondProduct.getProductId());
        l_objBindVars[1] = WEB3OnlineDispDivDef.VALIDITY;
        List l_lisBondAutoExecLimitHistoryList = new ArrayList();

        try
        {
            // �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondAutoExecLimitHistoryList = l_queryProcessor.doFindAllQuery(
                BondAutoExecLimitActionRow.TYPE,
                l_strQueryCondition,
                l_strOrderBy,
                null,
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisBondAutoExecLimitHistoryList;
    }

    /**
     * (get�����������ꗗ)<BR>
     * �����������ꗗ��Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����������e�[�u�����������A�������ʂ�List���擾����B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@����ID = �����E����.getProductId()<BR>
     * �@@�@@�m�\�[�g�����n <BR>
     * �@@�@@�@@�������@@�~��<BR>
     * <BR>
     * �Q�j�@@List��ԋp����B<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF20080
     */
    public List getBondProductCouponList(BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondProductCouponList(BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID = �����E����.getProductId()
        String l_strQueryCondition = " product_id = ? ";

        //�������@@�~��
        String l_strOrderBy = " interest_payment_day desc ";
        Object[] l_objBindVars = new Object[1];
        l_objBindVars[0] = new Long(l_bondProduct.getProductId());
        List l_lisBondAutoExecLimitHistoryList = new ArrayList();

        try
        {
            // �P�j�@@�����������e�[�u�����������A�������ʂ�List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondAutoExecLimitHistoryList = l_queryProcessor.doFindAllQuery(
                BondProductCouponRow.TYPE,
                l_strQueryCondition,
                l_strOrderBy,
                null,
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBondAutoExecLimitHistoryList;
    }

    /**
     * (get���������X�g)<BR>
     * �������̃��X�g��ԋp����B<BR>
     * <BR>
     * �P�j���������̕␳<BR>
     * <BR>
     * �@@�P�|1�j�ȉ��̒ʂ�Ɍ���������������쐬����B<BR>
     * �@@�@@�@@�@@�@@" �،���ЃR�[�h = ? " + ����.��������������<BR>
     * <BR>
     * �@@�P�|�Q�j����.���������f�[�^�R���e�i�̐擪�Ɉȉ���ǉ�����B<BR>
     * �@@�@@�@@�@@�@@����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j�ȉ��̏����ō������}�X�^�e�[�u�����������A<BR>
     * �@@�@@������Row�I�u�W�F�N�g��List���擾���ĕԋp����B<BR>
     * <BR>�@@�@@
     *     [��������]<BR>
     * �@@�@@�@@�쐬������������������<BR>
     * �@@�@@�@@�쐬�������������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@����.�\�[�g����������<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����������)<BR>
     * �\�[�g����������<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF200AF
     */
    public List getBondProductList(
        String l_strInstitutionCode,
        String l_strQueryString,
        Object[] l_objQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBondProductList(String, String, Object, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strInstitutionCode == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j���������̕␳
        //   �P�|1�j�ȉ��̒ʂ�Ɍ���������������쐬����B
        //   " �،���ЃR�[�h = ? " + ����.��������������
        if (l_strQueryString != null)
        {
            String l_strTemp = l_strQueryString.trim();
            if ("and".equals(l_strTemp.substring(0,3)))
            {
                l_strQueryString = " institution_code = ? "  + l_strQueryString ;
            }
            else
            {
                l_strQueryString = " institution_code = ?  and "  + l_strQueryString ;
            }
        }
        else
        {
            l_strQueryString = " institution_code = ? ";

        }

        // �P�|�Q�j����.���������f�[�^�R���e�i�̐擪�Ɉȉ���ǉ�����B
        // �@@ ����.�،���ЃR�[�h
        List l_lisContainer = new ArrayList();
        l_lisContainer.add(l_strInstitutionCode);

        if (l_objQueryContainers != null)
        {
            for (int i = 0; i < l_objQueryContainers.length; i++)
            {
                l_lisContainer.add(l_objQueryContainers[i]);
            }
        }

        Object[] l_objBindVars = new Object[l_lisContainer.size()];
        l_lisContainer.toArray(l_objBindVars);

        //return List
        List l_lisBondProductList = new ArrayList();

        // �Q�j�ȉ��̏����ō������}�X�^�e�[�u����������
        //�@@  ������Row�I�u�W�F�N�g��List���擾���ĕԋp����B
        try
        {
            // �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondProductList = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_objBindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisBondProductList;
    }

    /**
     * (update���������e)<BR>
     * ������DB�X�V���s���B<BR>
     * <BR>
     * �P�jthis.get������(Institution, String)���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@Institution������.�،����<BR>
     * �@@�@@String�@@�@@�@@�������R�[�h(WEB3)<BR>
     * <BR>
     * �Q�j������Params�I�u�W�F�N�g�̃N���[���𐶐�����B <BR>
     * <BR>
     * �R�j�N���[���Ƀv���p�e�B���Z�b�g����B <BR>
     * �@@�@@���Z�b�g���e�́A<BR>
     * �@@�@@DB�X�V�d�l�u�������o�^_�������}�X�^�e�[�u��DB�X�V�d�l.xls�v���Q��<BR>
     * <BR>
     * �S�j�N���[���̓��e��DB���X�V����B <BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)<BR>
     * @@param l_productUpdateInfo - (�������X�V���)<BR>
     * �������X�V���I�u�W�F�N�g<BR>
     * @@param l_strAdminCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF2010D
     */
    public void updateBondProductSpec(
        Institution l_institution,
        String l_strProductCode,
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo,
        String l_strAdminCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateBondProductSpec(Institution, String, WEB3AdminBondProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_productUpdateInfo == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�jthis.get������(Institution, String)���R�[������B
        // �@@[����]
        // �@@�@@Institution������.�،����
        // �@@�@@String�@@�@@�@@�������R�[�h(WEB3)
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)this.getBondProduct(
                l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        BondProductRow l_bondProductRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        BondProductParams l_bondProductParams = new BondProductParams(l_bondProductRow);

        //�戵�敪�@@�@@�@@�@@�@@�@@�F �������X�V���.�戵�敪
        l_bondProductParams.setTradeHandleDiv(l_productUpdateInfo.tradeHandleDiv);

        //�戵�J�n�����@@�@@�@@�@@�F �������X�V���.�戵�J�n����
        l_bondProductParams.setTradeStartDate(l_productUpdateInfo.tradeStartDate);

        //�戵�I�������@@�@@�@@�@@�F �������X�V���.�戵�I������
        l_bondProductParams.setTradeEndDate(l_productUpdateInfo.tradeEndDate);

        //����J�n���@@�@@�@@�@@�@@�F �������X�V���.����J�n��
        l_bondProductParams.setRecruitStartDate(l_productUpdateInfo.recruitStartDate);

        //����I�����@@�@@�@@�@@�@@�F �������X�V���.����I����
        l_bondProductParams.setRecruitEndDate(l_productUpdateInfo.recruitEndDate);     

        //�����敪�@@�@@�@@�@@�@@�@@�F �������X�V���.�����敪
        l_bondProductParams.setTradeType(l_productUpdateInfo.buySellDiv);

        //�������@@�@@�@@�@@�@@�F �������X�V���.������
        l_bondProductParams.setProductName(l_productUpdateInfo.productName);
        
        //���t�P���@@�@@�@@�@@�@@�@@�F �������X�V���.���t�P��
        if (l_productUpdateInfo.buyPrice != null)
        {
            l_bondProductParams.setBuyPrice(new Double(l_productUpdateInfo.buyPrice));
        }
        else
        {
            l_bondProductParams.setBuyPrice(null);
        }

        //���p�P���@@�@@�@@�@@�@@�@@�F �������X�V���.���p�P��
        if (l_productUpdateInfo.sellPrice != null)
        {
            l_bondProductParams.setSellPrice(new Double(l_productUpdateInfo.sellPrice));
        }
        else
        {
            l_bondProductParams.setSellPrice(null);
        }
        
        //�\���P�ʁ@@�@@�@@�@@�@@�@@�F �������X�V���.�\���P��
        l_bondProductParams.setTradeUnit(Double.parseDouble(l_productUpdateInfo.tradeUnit));
        
        //�Œ�z�ʁ@@�@@�@@�@@�@@�@@�F �������X�V���.�Œ�z��
        l_bondProductParams.setMinFaceAmount(Double.parseDouble(l_productUpdateInfo.minFaceAmount));
        
        //�ō��z�ʁ@@�@@�@@�@@�@@�@@�F �������X�V���.�ō��z��
        if (l_productUpdateInfo.maxFaceAmount != null)
        {
            l_bondProductParams.setMaxFaceAmount(Double.parseDouble(l_productUpdateInfo.maxFaceAmount));
        }
        else
        {
            l_bondProductParams.setMaxFaceAmount(null);
        }

        //�J�����_�[�A�g�s��R�[�h�@@�F �������X�V���.�J�����_�[�A�g�s��R�[�h
        l_bondProductParams.setCalLinkedMarketCode(l_productUpdateInfo.calendarLinkedDiv);
        
        //���t��n���ړ������@@�F �������X�V���.���t��n���ړ�����
        if (l_productUpdateInfo.buyDeliveryMove != null)
        {
            l_bondProductParams.setBuyDeliveryDateShiftdays(Integer.parseInt(l_productUpdateInfo.buyDeliveryMove));
        }
        else
        {
            l_bondProductParams.setBuyDeliveryDateShiftdays(null);
        }

        //���p��n���ړ������@@�F �������X�V���.���p��n���ړ�����       
        if (l_productUpdateInfo.sellDeliveryMove != null)
        {
            l_bondProductParams.setSellDeliveryDateShiftdays(Integer.parseInt(l_productUpdateInfo.sellDeliveryMove));
        }
        else
        {
            l_bondProductParams.setSellDeliveryDateShiftdays(null);
        }

        //�������敪�@@�@@�@@�@@�F �������X�V���.�������敪
        l_bondProductParams.setAutoExecDiv(l_productUpdateInfo.autoExecDiv);

        //�������g�@@�@@�@@�@@�@@�F �������X�V���.�������g
        if (l_productUpdateInfo.autoExecLimit != null)
        {
            l_bondProductParams.setAutoExecLimit(Double.parseDouble(l_productUpdateInfo.autoExecLimit));
        }
        else
        {
            l_bondProductParams.setAutoExecLimit(null);
        }

        //�J�X�g�f�B�A���R�[�h�F �������X�V���.�J�X�g�f�B�A���R�[�h
        l_bondProductParams.setCustodianCode(l_productUpdateInfo.custodianCode);

        //�d�����̈בփ��[�g�@@�F �������X�V���.�d�����̈בփ��[�g
        if (l_productUpdateInfo.fxRateAtStock != null)
        {
            l_bondProductParams.setBuyingFxRate(Double.parseDouble(l_productUpdateInfo.fxRateAtStock));
        }
        else
        {
            l_bondProductParams.setBuyingFxRate(null);
        }

        //������ԃ`�F�b�N�敪�F �������X�V���.������ԃ`�F�b�N�敪
        l_bondProductParams.setTradingTimeCheckDiv(l_productUpdateInfo.tradeTimeCheckDiv);

        //����萔�����@@�@@�@@�@@�F �������X�V���.����萔����
        if (l_productUpdateInfo.mediatorCommissionRate != null)
        {
            l_bondProductParams.setMediatorCommissionRate(new Double(l_productUpdateInfo.mediatorCommissionRate));
        }
        else
        {
            l_bondProductParams.setMediatorCommissionRate(null);
        }

        //�ŏI�X�V�҃R�[�h�@@�@@�F ����.�Ǘ��҃R�[�h
        l_bondProductParams.setLastUpdater(l_strAdminCode);

        //�@@�Ǘ��ҍX�V�����@@�@@�@@�F ���ݓ���
        l_bondProductParams.setAdminLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�@@�X�V���t�@@�@@�@@�@@�@@�@@�F ���ݓ���
        l_bondProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //���助�U�`��
        l_bondProductParams.setRecruitInvitationDiv(l_productUpdateInfo.recruitInvitationForm);

        //������󂯋敪
        l_bondProductParams.setRecruitAcceptDiv(l_productUpdateInfo.recruitAcceptDiv);

        //��n��
        l_bondProductParams.setDeliveryDate(l_productUpdateInfo.deliveryDate);

        //�R�j�N���[���̓��e��DB���X�V����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�X���b�h�ԍ������ɐ�������PrimaryKey�I�u�W�F�N�g
            l_queryProcessor.doUpdateQuery(l_bondProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�戵�\��ʃR�[�h�ꗗ)<BR>
     * �������}�X�^�Ŏ戵�\�Ȏ�ʃR�[�h�̈ꗗ����������B<BR>
     * <BR>
     * �P)�ꎞ�ۑ��p��List���쐬�B<BR>
     * <BR>
     * �Q�j�����}�X�^�Ō������s���B<BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h�������̏،���ЃR�[�h<BR>
     * �@@�戵�敪 != 0�F�s��<BR>
     * �@@[�\�[�g����] <BR>
     * �@@��ʃR�[�h�@@���� <BR>
     * <BR>
     * �R�j�������ʂ̌������A���L������J��Ԃ����s<BR>
     * �@@�R�|�P�j�����̎�ʃR�[�h���擾�B<BR>
     * �@@�R�|�Q�jList�̒��ɓ�����ʃR�[�h�����݂��邩�ǂ����𔻒f����<BR>
     * �@@�R�|�R�j���݂��Ȃ��ꍇ�FList�ɒǉ��A���݂���ꍇ�FList�ɒǉ����Ȃ�<BR>
     * <BR>
     * �S�jList���ʂ�String�z��ɕϊ����A�Ԃ��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     *
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 44C46848017A
     */
    public String[] getTradeHandlingPossibleBondCategCodeList(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeHandlingPossibleBondCategCodeList(String)";
        log.entering(STR_METHOD_NAME);

        //�P)�ꎞ�ۑ��p��List���쐬�B
        List l_lisReturn = new ArrayList();
        List l_lisTradeHandleDivList = new ArrayList();

        //�Q�j�����}�X�^�Ō������s���B
        // �@@[��������]
        // �@@�،���ЃR�[�h�������̏،���ЃR�[�h
        // �@@�戵�敪 != 0�F�s��
        //   �@@[�\�[�g����] 
        //    �@@��ʃR�[�h�@@���� 
        String l_strQueryCondition = " institution_code = ? and trade_handle_div != ? ";
        String l_strSortCond = " " + WEB3BondProductSortKeyItemDef.BOND_CATEG_CODE + " asc";
        
        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = l_strInstitutionCode;
        l_objBindVars[1] = WEB3TradeHandleDivDef.DISABLED;

        try
        {
            // �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisTradeHandleDivList = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQueryCondition,
                l_strSortCond,
                null,
                l_objBindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        int l_intSize = 0;
        if (l_lisTradeHandleDivList != null && !l_lisTradeHandleDivList.isEmpty())
        {
            l_intSize = l_lisTradeHandleDivList.size();
        }

        //�R�j�������ʂ̌������A���L������J��Ԃ����s
        for (int i = 0; i < l_intSize; i++)
        {
            //�R�|�P�j�����̎�ʃR�[�h���擾�B
            boolean l_blnFlag = false;
            String l_strTemp = ((BondProductRow)l_lisTradeHandleDivList.get(i)).getBondCategCode();
            if (l_strTemp == null)
            {
            	continue;
            }
            for (int j = 0; j < i; j++)
            {
                //�R�|�Q�jList�̒��ɓ�����ʃR�[�h�����݂��邩�ǂ����𔻒f����
                if (l_strTemp.equals(((BondProductRow)l_lisTradeHandleDivList.get(j)).getBondCategCode()))
                {
                    l_blnFlag = true;
                }
            }
            //�R�|�R�j���݂��Ȃ��ꍇ�FList�ɒǉ��A���݂���ꍇ�FList�ɒǉ����Ȃ�
            if (!l_blnFlag)
            {
                l_lisReturn.add(l_strTemp);
            }
        }

        //�S�jList���ʂ�String�z��ɕϊ����A�Ԃ��B
        String[] l_strs = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strs);
        return l_strs;
    }

    /**
     * (get������)<BR>
     * �igetBondProduct(Institution, String, String)�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ���������擾����B <BR>
     * <BR>
     * �P�j�@@�������}�X�^�e�[�u��������Params���擾����B <BR>
     * �|�ȉ��̏����ō������e�[�u�����������A������Params���擾����B <BR>
     * �@@�@@[��������] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.�،����.get�،���ЃR�[�h() <BR>
     * �@@�@@�@@�����R�[�h(WEB3)�F����.�����R�[�h(WEB3) <BR>
     * �@@�@@�@@�񍆃R�[�h(WEB3)�F����.�񍆃R�[�h <BR>
     * <BR>
     * �Q�j�@@�������I�u�W�F�N�g���擾����B <BR>
     * �@@this.to����()���R�[�����āA�������I�u�W�F�N�g���擾����B<BR>
     * �@@[to�����ɓn���p�����^�n <BR>
     * �@@�@@Params�I�u�W�F�N�g�F 1)�Ŏ擾����������Params�I�u�W�F�N�g<BR>
     *<BR>
     * �R�j�@@�擾�����������I�u�W�F�N�g��Ԃ��B <BR>
     * @@param l_institution - (�،����)<BR>
     * @@param l_strProductCode - (�����R�[�h(WEB3))<BR>
     * @@param l_strIssueCode - (�񍆃R�[�h)<BR>
     * �񍆃R�[�h<BR>
     * @@return BondProduct
     * @@throws NotFoundException
     * @@roseuid 44D67EBE00D9
     */
    public BondProduct getBondProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strIssueCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBondProduct(Institution, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strInstitutionCode = l_institution.getInstitutionCode();
        BondProductRow l_row = null;
        try
        {
            l_row =
                BondProductDao.findRowByInstitutionCodeProductCodeProductIssueCode(
                l_strInstitutionCode,
                l_strProductCode,
                l_strIssueCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null)
        {
            String msg = "No Bond product found with institutionCode,productCode and productIssueCode :"
                + l_strInstitutionCode + "," +
                l_strProductCode + "," +
                l_strIssueCode;
            throw new NotFoundException(msg);
        }

        // �Q�j�@@�������I�u�W�F�N�g���擾����B
        //  this.to����()���R�[�����āA�������I�u�W�F�N�g���擾����B
        BondProductParams l_bondProductParams = new BondProductParams(l_row);
        BondProduct l_bondProduct = (BondProduct)this.toProduct(l_bondProductParams);

        //�R�j�@@�擾�����������I�u�W�F�N�g��Ԃ��B
        return l_bondProduct;
    }

    /**
     * (validate�������e)<BR>
     * �����������X�V���̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �P�jthis.get�������i�j�����������擾����B<BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�@@����ID�@@�F����.����ID <BR>
     * <BR>
     * �Q�j����J�n���iWEB3)�`�F�b�N <BR>
     * �@@�@@�Q�|�P�j�����������X�V���.����J�n���iWEB3)����c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3�j����c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02856<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j������.����J�n��(SONAR�j ��<BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����J�n���iWEB3)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���iSONAR)�Ɖ���J�n���iWEB3)<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02857<BR>
     * <BR>
     * �R�j����I�����iWEB3)�`�F�b�N <BR>
     * �@@�@@�R�|�P�j�����������X�V���.����I�����iWEB3)����c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����I�����iWEB3)����c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02858<BR>
     * <BR>
     * �@@�@@�R�|�Q�j������.����I�����iSONAR�j ��<BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����I�����iWEB3)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����I�����iSONAR)�Ɖ���I�����iWEB3)<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02859<BR>
     * <BR>
     * �@@�@@�R�|�R�j�����������X�V���.����J�n���iWEB3) ��<BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����I�����iWEB3)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3)�Ɖ���I�����iWEB3�j<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02860<BR>
     * <BR>
     * �S�j����J�n���i�C���^�[�l�b�g�j�`�F�b�N <BR>
     * �@@�@@�S�|�P�j�����������X�V���.����J�n���i�C���^�[�l�b�g�j����c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02861<BR>
     * <BR>
     * �@@�@@�S�|�Q�j�����������X�V���.����J�n���iWEB3) ��<BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����J�n���i�C���^�[�l�b�g�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02862<BR>
     * <BR>
     * �T�j����I�����i�C���^�[�l�b�g�j�`�F�b�N <BR>
     * �@@�@@�T�|�P�j�����������X�V���.����I�����i�C���^�[�l�b�g�j����c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02863<BR>
     * <BR>
     * �@@�@@�T�|�Q�j�����������X�V���.����I�����iWEB3) ��<BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����I�����i�C���^�[�l�b�g�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02864<BR>
     * <BR>
     * �@@�@@�T�|�R�j�����������X�V���.����J�n���i�C���^�[�l�b�g) �� <BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.����I�����i�C���^�[�l�b�g�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j<BR>
     * �@@�@@�@@�@@�@@�@@�̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02868<BR>
     * <BR>
     * �U�j��n���`�F�b�N <BR>
     * �@@�@@�U�|�P�j�����������X�V���.��n������c�Ɠ��̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u��n������c�Ɠ��ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02336<BR>
     * <BR>
     * �@@�@@�U�|�Q�j�����������X�V���.����I�����iWEB3�j + 1�c�Ɠ� �� <BR>
     * �@@�@@�@@�@@�@@�@@�����������X�V���.��n�� �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u��n�����s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02865<BR>
     * <BR>
     * �@@�@@�U�|�R�j�����������X�V���.��n�� �� ������.���s���̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u��n�����s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02865<BR>
     * <BR>
     * �V�j�\���P�ʃ`�F�b�N <BR>
     * �@@�@@������.�ŏ����s���� �� null�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�V�|�P�j�����������X�V���.�\���P�ʂ�������.�ŏ����s���� �~ 1000<BR>
     * �@@�@@�@@�@@�@@�@@�̐����{�łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u�\���P�ʂ��s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02866<BR>
     * <BR>
     * �W�j�Œ�z�ʃ`�F�b�N <BR>
     * �@@�@@������.�ŏ����s���� �� null�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�W�|�P�j�����������X�V���.�Œ�z�� �� ������.�ŏ����s���� �~ 1000<BR>
     * �@@�@@�@@�@@�@@�@@ �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u�Œ�z�ʂ��s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02867<BR>
     * <BR>
     * �X�j�ō��z�ʃ`�F�b�N <BR>
     * �@@�@@�X�|�P�j�����������X�V���.�Œ�z�� �� �����������X�V���.�ō��z�ʂ̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�G���[�u�Œ�z�ʂƍō��z�ʂ̊֌W���s���ł��B�v���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02625<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_bondDomesticProductUpdateInfo - (�����������X�V���)<BR>
     * �����������X�V���<BR>
     * @@throws WEB3BaseException
     */
    public void validateProductSpec(
        String l_strProductId,
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateProductSpec(String, WEB3BondDomesticProductUpdateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_strProductId == null || l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�jthis.get�������i�j�����������擾����B
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)this.getBondProduct(Long.parseLong(l_strProductId));

        //�Q�j����J�n���iWEB3)�`�F�b�N
        //�@@�@@�Q�|�P�j�����������X�V���.����J�n���iWEB3)����c�Ɠ��̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3�j����c�Ɠ��ł��B�v���X���[����B
        Timestamp l_tsRecruitStartDateWEB3 =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitStartDateWEB3.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitStartDateWEB3)))
        {
            log.debug("����J�n���iWEB3�j����c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02856,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���iWEB3�j����c�Ɠ��ł��B");
        }

        //�@@�@@�Q�|�Q�j������.����J�n��(SONAR�j �� �����������X�V���.����J�n���iWEB3)�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���iSONAR)�Ɖ���J�n���iWEB3)�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondProduct.getHostRecruitStartDate(),
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3) > 0)
        {
            log.debug("����J�n���iSONAR)�Ɖ���J�n���iWEB3)�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02857,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���iSONAR)�Ɖ���J�n���iWEB3)�̊֌W���s���ł��B");
        }

        //�R�j����I�����iWEB3)�`�F�b�N
        //�@@�@@�R�|�P�j�����������X�V���.����I�����iWEB3)����c�Ɠ��̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����I�����iWEB3)����c�Ɠ��ł��B�v���X���[����B
        Timestamp l_tsRecruitEndDateWEB3 =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitEndDateWEB3.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitEndDateWEB3)))
        {
            log.debug("����I�����iWEB3)����c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02858,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����iWEB3)����c�Ɠ��ł��B");
        }

        //�@@�@@�R�|�Q�j������.����I�����iSONAR�j �� �����������X�V���.����I�����iWEB3)�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����I�����iSONAR)�Ɖ���I�����iWEB3)�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondProduct.getHostRecruitEndDate(),
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3) <= 0)
        {
            log.debug("����I�����iSONAR)�Ɖ���I�����iWEB3)�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02859,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����iSONAR)�Ɖ���I�����iWEB3)�̊֌W���s���ł��B");
        }

        //�@@�@@�R�|�R�j�����������X�V���.����J�n���iWEB3) �� �����������X�V���.����I�����iWEB3)�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3)�Ɖ���I�����iWEB3�j�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3) > 0)
        {
            log.debug("����J�n���iWEB3)�Ɖ���I�����iWEB3�j�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02860,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���iWEB3)�Ɖ���I�����iWEB3�j�̊֌W���s���ł��B");
        }

        //�S�j����J�n���i�C���^�[�l�b�g�j�`�F�b�N
        //�@@�@@�S�|�P�j�����������X�V���.����J�n���i�C���^�[�l�b�g�j����c�Ɠ��̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B�v���X���[����B
        Timestamp l_tsRecruitStartDateInterNet =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitStartDateInterNet.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitStartDateInterNet)))
        {
            log.debug("����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02861,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B");
        }

        //�@@�@@�S�|�Q�j�����������X�V���.����J�n���iWEB3) �� �����������X�V���.����J�n���i�C���^�[�l�b�g�j�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet) > 0)
        {
            log.debug("����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02862,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
        }

        //�T�j����I�����i�C���^�[�l�b�g�j�`�F�b�N
        //�@@�@@�T�|�P�j�����������X�V���.����I�����i�C���^�[�l�b�g�j����c�Ɠ��̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B�v���X���[����B
        Timestamp l_tsRecruiEndDateInterNet =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitEndDateInterNet.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruiEndDateInterNet)))
        {
            log.debug("����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02863,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B");
        }

        //�@@�@@�T�|�Q�j�����������X�V���.����I�����iWEB3) �� �����������X�V���.����I�����i�C���^�[�l�b�g�j�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet) < 0)
        {
            log.debug("����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02864,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
        }

        //�@@�@@�T�|�R�j�����������X�V���.����J�n���i�C���^�[�l�b�g) �� �����������X�V���.����I�����i�C���^�[�l�b�g�j�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet,
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet) > 0)
        {
            log.debug("����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02868,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");
        }

        //�U�j��n���`�F�b�N
        //�@@�@@�U�|�P�j�����������X�V���.��n������c�Ɠ��̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u��n������c�Ɠ��ł��B�v���X���[����B
        Timestamp l_tsDeliveryDate =
            new Timestamp(l_bondDomesticProductUpdateInfo.deliveryDate.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDeliveryDate)))
        {
            log.debug("��n������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n������c�Ɠ��ł��B");
        }

        //�@@�@@�U�|�Q�j�����������X�V���.����I�����iWEB3�j + 1�c�Ɠ� �� �����������X�V���.��n�� �̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u��n�����s���ł��B�v���X���[����B
        Date l_datNextDeliveryDate = new WEB3GentradeBizDate(l_tsRecruitEndDateWEB3).roll(1);
        if (WEB3DateUtility.compareToDay(
            l_datNextDeliveryDate,
            l_bondDomesticProductUpdateInfo.deliveryDate) > 0)
        {
            log.debug("��n�����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n�����s���ł��B");
        }

        //�@@�@@�U�|�R�j�����������X�V���.��n�� �� ������.���s���̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u��n�����s���ł��B�v���X���[����B
        if (WEB3DateUtility.compareToDay(
            l_bondDomesticProductUpdateInfo.deliveryDate,
            l_bondProduct.getIssueDate()) >= 0)
        {
            log.debug("��n�����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n�����s���ł��B");
        }

        //�V�j�\���P�ʃ`�F�b�N
        //�@@�@@������.�ŏ����s���� �� null�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        if (l_bondProduct.getMinIssueCouponType() != null)
        {
            //�V�|�P�j�����������X�V���.�\���P�ʂ�������.�ŏ����s���� �~ 1000 �̐����{�łȂ��ꍇ�A
            //�@@�@@�@@�@@�G���[�u�\���P�ʂ��s���ł��B�v���X���[����B
            long l_lngApplyUnit = Long.parseLong(l_bondDomesticProductUpdateInfo.applyUnit);
            long l_lngMinIssueCouponType =
                (new BigDecimal(l_bondProduct.getMinIssueCouponType()).multiply(
                    new BigDecimal("1000"))).longValue();
            if (l_lngApplyUnit % l_lngMinIssueCouponType != 0)
            {
                log.debug("�\���P�ʂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02866,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\���P�ʂ��s���ł��B");
            }
        }

        //�����������X�V���.�Œ�z��
        BigDecimal l_bdMinFaceAmount = new BigDecimal(l_bondDomesticProductUpdateInfo.minFaceAmount);

        //�W�j�Œ�z�ʃ`�F�b�N
        //�@@�@@������.�ŏ����s���� �� null�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        if (l_bondProduct.getMinIssueCouponType() != null)
        {
            //�W�|�P�j�����������X�V���.�Œ�z�� �� ������.�ŏ����s���� �~ 1000 �̏ꍇ�A
            //�@@�@@�@@�@@�G���[�u�Œ�z�ʂ��s���ł��B�v���X���[����B
            BigDecimal l_bdMinIssueCouponType =
                new BigDecimal(l_bondProduct.getMinIssueCouponType()).multiply(new BigDecimal("1000"));
            if (l_bdMinFaceAmount.compareTo(l_bdMinIssueCouponType) < 0)
            {
                log.debug("�Œ�z�ʂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02867,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Œ�z�ʂ��s���ł��B");
            }
        }

        //�X�j�ō��z�ʃ`�F�b�N
        //�@@�@@�X�|�P�j�����������X�V���.�Œ�z�� �� �����������X�V���.�ō��z�ʂ̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�G���[�u�Œ�z�ʂƍō��z�ʂ̊֌W���s���ł��B�v���X���[����B
        BigDecimal l_bdMaxFaceAmount = new BigDecimal(l_bondDomesticProductUpdateInfo.maxFaceAmount);
        if (l_bdMinFaceAmount.compareTo(l_bdMaxFaceAmount) > 0)
        {
            log.debug("�Œ�z�ʂ��ō��z�ʂ𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02625,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Œ�z�ʂ��ō��z�ʂ𒴂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update���������e)<BR>
     * �����������X�V�����������}�X�^�e�[�u���̍X�V�������s���B<BR>
     * <BR>
     * �P�jthis.get������()�����������擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID�@@�F����.����ID<BR>
     * <BR>
     * �Q�j�擾����������Params�I�u�W�F�N�g�̃N���[���𐶐�����B<BR>
     * <BR>
     * �R�j�N���[���Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     *    ��DB�X�V�d�l�Q��<BR>
     * <BR>
     * �S�j�N���[���̓��e�ō������}�X�^�e�[�u�����X�V����B<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID
     * @@param l_bondDomesticProductUpdateInfo - (�����������X�V���)<BR>
     * �����������X�V���
     * @@param l_administratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h
     * @@throws WEB3BaseException
     */
    public void updateBondProductContent(
        String l_strProductId,
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo,
        String l_administratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateBondProductContent(String, WEB3BondDomesticProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strProductId == null || l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�jthis.get������()�����������擾����B
        BondProduct l_bondProduct = this.getBondProduct(Long.parseLong(l_strProductId));

        // �Q�j�擾����������Params�I�u�W�F�N�g�̃N���[���𐶐�����B
        BondProductRow l_bondProductRow =
            (BondProductRow)l_bondProduct.getDataSourceObject();
        BondProductParams l_bondProductParams = new BondProductParams(l_bondProductRow);

        // �R�j�N���[���Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        // �戵�敪
        l_bondProductParams.setTradeHandleDiv(l_bondDomesticProductUpdateInfo.tradeHandleDiv);
        // �戵�J�n����
        // �����������X�V���.����J�n���iWEB3�j
        l_bondProductParams.setTradeStartDate(l_bondDomesticProductUpdateInfo.recruitStartDateWEB3);
        // �戵�I������
        l_bondProductParams.setTradeEndDate(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3);

        // ����J�n��
        // �����������X�V���.����J�n���i�C���^�[�l�b�g�j
        l_bondProductParams.setRecruitStartDate(
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet);
        // ����I����
        l_bondProductParams.setRecruitEndDate(
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet);

        // �����敪
        // �����������X�V���̓�����
        l_bondProductParams.setTradeType(l_bondDomesticProductUpdateInfo.dealingType);
        // ������
        // �����������X�V���̓�����
        l_bondProductParams.setProductName(l_bondDomesticProductUpdateInfo.productNameWEB3);
        //�\���P��
        // �����������X�V���̓�����
        l_bondProductParams.setTradeUnit(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.applyUnit));
        // �Œ�z��
        // �����������X�V���̓�����
        l_bondProductParams.setMinFaceAmount(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.minFaceAmount));
        // �ō��z��
        // �����������X�V���̓�����
        l_bondProductParams.setMaxFaceAmount(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.maxFaceAmount));
        // �ŏI�X�V�҃R�[�h
        // �Ǘ���.�Ǘ��҃R�[�h
        l_bondProductParams.setLastUpdater(l_administratorCode);
        // �Ǘ��ҍX�V���t
        l_bondProductParams.setAdminLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        // �X�V���t
        // ���ݎ���
        l_bondProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        // ��n��
        // �����������X�V���̓�����
        l_bondProductParams.setDeliveryDate(l_bondDomesticProductUpdateInfo.deliveryDate);
        // �ژ_�����{���`�F�b�N�敪
        // �����������X�V���̓�����
        l_bondProductParams.setProspectusCheckDiv(
            l_bondDomesticProductUpdateInfo.prospectusCheckDiv);

        // �S�j�N���[���̓��e�ō������}�X�^�e�[�u�����X�V����B
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doUpdateQuery(l_bondProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���s����)<BR>
     * ���s������擾����B<BR>
     * <BR>
     * �P�j�ȉ��̌��������ō����s����e�[�u�����������AList���擾����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�@@����ID = ����.����ID<BR>
     * <BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@�@@���s����@@����<BR>
     * <BR>
     * �Q�jString[]�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�Ŏ擾����List�̗v�f�����ȉ���LOOP�������s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�R�|�P�jString[]�ɍ����s����.���s����̒l��ǉ�����B<BR>
     * <BR>
     * �S�j�쐬����String[]��ԋp����B<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public String[] getIssueCouponType(String l_strProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIssueCouponType(String)";
        log.entering(STR_METHOD_NAME);

        List l_lisResults = null;
        try
        {
            //�P�j�ȉ��̌��������ō����s����e�[�u�����������AList���擾����B
            //   [��������]
            //    �@@����ID = ����.����ID
            //   [�\�[�g����]
            //      ���s����@@����
            String l_strProductIdString = " product_id = ? ";
            String l_strIssueCouponTypeString = "issue_coupon_type asc";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    BondIssueCouponTypeRow.TYPE,
                    l_strProductIdString,
                    l_strIssueCouponTypeString,
                    null,
                    new Object[]{l_strProductId});
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intCnt = 0;
        if (!l_lisResults.isEmpty())
        {
            l_intCnt = l_lisResults.size();
        }

        //�Q�jString[]�𐶐�����B
        String[] l_strIssueCouponTypes = new String[l_intCnt];

        //�R�j�P�j�Ŏ擾����List�̗v�f�����ȉ���LOOP�������s�Ȃ��B
        BondIssueCouponTypeRow l_couponTypeRow = null;
        for (int i = 0; i < l_intCnt; i++)
        {
            //�R�|�P�jString[]�ɍ����s����.���s����̒l��ǉ�����B
            l_couponTypeRow = (BondIssueCouponTypeRow)l_lisResults.get(i);
            l_strIssueCouponTypes[i] = l_couponTypeRow.getIssueCouponType();
        }

        //�S�j�쐬����String[]��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strIssueCouponTypes;
    }

    /**
     * (get��������t�����ꗗ)<BR>
     * ��������t�����s�I�u�W�F�N�g�̃��X�g���擾����B<BR>
     *<BR>
     * �P�j��������t�����e�[�u�����������A��������t�����s<BR>
     * �@@�@@�I�u�W�F�N�g�̃��X�g���擾����B<BR>
     *<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�@@����ID = ����.����ID<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     *<BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@�@@������t���t�@@�@@����<BR>
     *<BR>
     * �Q�j�擾������������t�����s�I�u�W�F�N�g�̃��X�g��ԋp����B<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getBondOrderReceiveHistoryList(
        String l_strProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBondOrderReceiveHistoryList(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //��������t�����e�[�u�����������A��������t�����s�I�u
        //�W�F�N�g�̃��X�g���擾����B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_sbWhere.append(" and institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");

        Object[] l_whereValues = new Object[3];
        l_whereValues[0] = l_strProductId;
        l_whereValues[1] = l_strInstitutionCode;
        l_whereValues[2] = l_strBranchCode;

        //[�\�[�g����]
        //������t���t�@@�@@����
        String l_strOrderBy = " order_accept_date asc ";

        List l_lisBondOrderAcceptAction = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderAcceptAction = l_queryProcessor.doFindAllQuery(
                BondOrderAcceptActionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_whereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�擾������������t�����s�I�u�W�F�N�g�̃��X�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisBondOrderAcceptAction;
    }

    /**
     * (create���������X�ʉ���g���)<BR>
     * ���������X�ʉ���g���̔z����쐬����B<BR>
     *<BR>
     * �P�j�����X�ʉ���gRow�̃��X�g���擾�B<BR>
     *<BR>
     * �@@�@@�P�|�P�j�ȉ��̌��������ō����X�ʉ���g�e�[�u�����������A<BR>
     * �@@�@@�@@�@@�@@�@@�����X�ʉ���gRow�̃��X�g���擾����B<BR>
     *<BR>
     * �@@�@@�@@�@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�@@�@@�@@����ID�@@���@@����.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�@@���@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@(*1)���X�R�[�h�@@���@@����.���X�R�[�h<BR>
     *<BR>
     * �@@�@@�@@�@@�@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@����<BR>
     *<BR>
     * �@@�@@�@@�@@�@@�@@�@@(*1)����.���X�R�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���X�R�[�h�̌��������͍쐬���Ȃ��B<BR>
     *<BR>
     * �@@�@@�P�|�Q�j�����X�ʉ���g�e�[�u���̌������ʂ�0���̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�v�f��0�̍��������X�ʉ���g���̔z���ԋp����B<BR>
     *<BR>
     * �Q�j���������X�ʉ���g���̔z����쐬�B<BR>
     * �@@�@@�擾���������X�ʉ���gRow�̃��X�g�̗v�f����LOOP�������s�Ȃ��A<BR>
     * �@@�@@���������X�ʉ���g���̔z����쐬����B<BR>
     *<BR>
     * �@@�@@�Q�|�P�j���������X�ʉ���g���C���X�^���X�𐶐�����B<BR>
     *<BR>
     * �@@�@@�Q�|�Q�j�������z���v���擾�B<BR>
     * �@@�@@�@@�@@�@@�@@�g���������}�l�[�W��.calc�������������z���v()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�������z���v���擾����B<BR>
     *<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����ID�@@�F�@@�����X�ʉ���gRow.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�@@�F�@@�����X�ʉ���gRow.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@�F�@@�����X�ʉ���gRow.���X�R�[�h<BR>
     *<BR>
     * �@@�@@�Q�|�R�j�����������������X�ʉ���g���C���X�^���X��<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     *<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������z���v�@@���@@calc�������������z���v()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@WEB3����g�@@�@@���@@�����X�ʉ���gRow.����g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@�@@�@@�@@���@@�����X�ʉ���gRow.���X�R�[�h<BR>
     *<BR>
     * �R�j�쐬�������������X�ʉ���g���̔z���ԋp����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitInfo[] - ���������X�ʉ���g���[]
     * @@throws WEB3BaseException
     */
    public WEB3BondDomesticBranchRecruitLimitInfo[] createAdminBondDomesticRecruitLimitInfo(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAdminBondDomesticRecruitLimitInfo(long, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�����X�ʉ���gRow�̃��X�g���擾�B
        //�P�|�P�j�ȉ��̌��������ō����X�ʉ���g�e�[�u�����������A
        //�@@�@@�@@�@@�����X�ʉ���gRow�̃��X�g���擾����B
        //[��������]
        //  ����ID�@@���@@����.����ID
        //  �،���ЃR�[�h�@@���@@����.�،���ЃR�[�h
        //  (*1)���X�R�[�h�@@���@@����.���X�R�[�h
        List l_lisValues = new ArrayList();

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_lisValues.add(new Long(l_lngProductId));
        l_sbWhere.append(" AND institution_code = ? ");
        l_lisValues.add(l_strInstitutionCode);

        //(*1)����.���X�R�[�h == null �̏ꍇ�A���X�R�[�h�̌��������͍쐬���Ȃ��B
        if (l_strBranchCode != null)
        {
            l_sbWhere.append(" AND  branch_code = ? ");
            l_lisValues.add(l_strBranchCode);
        }

        Object[] l_whereValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_whereValues);

        //[�\�[�g����]
        //���X�R�[�h�@@����
        String l_strOrderBy = " branch_code ASC ";

        //�����X�ʉ���gRow�̃��X�g���擾����B
        List l_lisBondBranchRecruitLimit = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondBranchRecruitLimit = l_queryProcessor.doFindAllQuery(
                BondBranchRecruitLimitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_whereValues);

            //�P�|�Q�j�����X�ʉ���g�e�[�u���̌������ʂ�0���̏ꍇ�A
            //�@@�@@�@@�@@�v�f��0�̍��������X�ʉ���g���̔z���ԋp����B
            if (l_lisBondBranchRecruitLimit.isEmpty())
            {
                WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos =
                    new WEB3BondDomesticBranchRecruitLimitInfo[]{};
                return l_bondDomesticBranchRecruitLimitInfos;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j���������X�ʉ���g���̔z����쐬�B
        //�擾���������X�ʉ���gRow�̃��X�g�̗v�f����LOOP�������s�Ȃ��A
        //���������X�ʉ���g���̔z����쐬����B
        List l_lisBondDomesticBranchRecruitLimitInfos = new ArrayList();
        Iterator l_iteratorBondBranchRecruitLimit = l_lisBondBranchRecruitLimit.iterator();

        while (l_iteratorBondBranchRecruitLimit.hasNext())
        {
            BondBranchRecruitLimitRow l_bondBranchRecruitLimitRow =
                (BondBranchRecruitLimitRow)l_iteratorBondBranchRecruitLimit.next();

            //�Q�|�P�j���������X�ʉ���g���C���X�^���X�𐶐�����B
            WEB3BondDomesticBranchRecruitLimitInfo l_bondDomesticBranchRecruitLimitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo();

            //�Q�|�Q�j�������z���v���擾�B
            //�@@[����]
            //�@@�@@�@@����ID�@@�F�@@�����X�ʉ���gRow.����ID
            //�@@�@@�@@�،���ЃR�[�h�@@�F�@@�����X�ʉ���gRow.�،���ЃR�[�h
            //�@@�@@�@@���X�R�[�h�@@�F�@@�����X�ʉ���gRow.���X�R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.BOND);
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager)l_tradingModule.getOrderManager();
            double l_dblCalcBondDomesticOrderAmountTotal = l_bondOrderManager.calcBondDomesticOrderAmountTotal(
                l_bondBranchRecruitLimitRow.getProductId(),
                l_bondBranchRecruitLimitRow.getInstitutionCode(),
                l_bondBranchRecruitLimitRow.getBranchCode());

            //�Q�|�R�j�����������������X�ʉ���g���C���X�^���X��
            //   �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
            //�������z���v�@@���@@calc�������������z���v()�̖߂�l
            l_bondDomesticBranchRecruitLimitInfo.orderAmountTotal =
                WEB3StringTypeUtility.formatNumber(l_dblCalcBondDomesticOrderAmountTotal);
            //WEB3����g�@@�@@���@@�����X�ʉ���gRow.����g
            l_bondDomesticBranchRecruitLimitInfo.web3RecruitLimit =
                WEB3StringTypeUtility.formatNumber(l_bondBranchRecruitLimitRow.getRecruitLimit());
            //���X�R�[�h�@@�@@�@@�@@���@@�����X�ʉ���gRow.���X�R�[�h
            l_bondDomesticBranchRecruitLimitInfo.branchCode = l_bondBranchRecruitLimitRow.getBranchCode();

            l_lisBondDomesticBranchRecruitLimitInfos.add(l_bondDomesticBranchRecruitLimitInfo);
        }

        //�R�j�쐬�������������X�ʉ���g���̔z���ԋp����B
        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos =
            new WEB3BondDomesticBranchRecruitLimitInfo[l_lisBondDomesticBranchRecruitLimitInfos.size()];
        l_lisBondDomesticBranchRecruitLimitInfos.toArray(l_bondDomesticBranchRecruitLimitInfos);

        log.exiting(STR_METHOD_NAME);
        return l_bondDomesticBranchRecruitLimitInfos;
    }
}
@
