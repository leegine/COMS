head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���|�W�V�����}�l�[�W�� (WEB3BondPositionManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 ����(���u) �V�K�쐬
                    2006/10/08 ���� (���u) �d�l�ύX�E���f��111
                    2006/10/12 �����(���u)WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (���|�W�V�����}�l�[�W��)<BR>
 * ���|�W�V�����}�l�[�W��
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3BondPositionManager extends BondPositionManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondPositionManager.class);

    /**
     * PositionManagerHelper��Override
     */
    public WEB3BondPositionManager()
    {
        super();
        super.m_helper = new WEB3BondPositionManagerHelper(ProductTypeEnum.BOND );
    }
    /**
     * (get���ۗL���Y�ꗗ)
     * �igetAssets�̃I�[�o�[���[�h�j <BR>
     * <BR>
     * �w������Ɉ�v������ۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@����������ǉ�����B <BR>
     * <BR>
     * �P�|�P�j�@@����.��������������̐擪�ɁA <BR>
     * "����ID = ? " + <BR>
     * "and�@@�⏕����ID = ? " + <BR>
     * "and �����^�C�v = ProductTypeEnum.�� " +<BR>
     * "and �~�j���敪 = 0�FDEFAULT�i�~�j���łȂ��j " + <BR>
     * "and (����+���t�s�\����) > 0 "<BR>
     * ��t������B<BR>
     * <BR>
     * �P�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@������ID�A�⏕����ID�́A�����̕⏕�����I�u�W�F�N�g���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ۗL���Y�I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * doFindAllQuery(,�ۗL���YRow.TYPE<BR>
     *                               �Q�|�P�j�̌�������������, <BR>
     *                               �����̃\�[�g����,<BR>
     *                               null,<BR>
     *                               �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     *<BR>
     * �S�j�@@�������ʂ��ۗL���Y�I�u�W�F�N�g�𐶐����A<BR>
     * �@@List�Ƃ��ĕԋp����B <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D0354A0000
     */
    public List getAssets(SubAccount l_subAccount,
        String l_strQueryString,
        Object[] l_objQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAssets(SubAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@����������ǉ�����B
        StringBuffer l_sbQueryString = new StringBuffer();
        //�@@"����ID = ? " +
        l_sbQueryString.append(" account_id = ?");
        //"and�@@�⏕����ID = ? " +
        l_sbQueryString.append(" and sub_account_id = ?");
        //"and �����^�C�v = ProductTypeEnum.�� " +
        l_sbQueryString.append(" and product_type = ?");
        //"and �~�j���敪 = 0�FDEFAULT�i�~�j���łȂ��j " +
        l_sbQueryString.append(" and mini_stock_div = ?");
        //"and (����+���t�s�\����) > 0 "
        l_sbQueryString.append(" and (quantity + quantity_cannot_sell) > ?  ");
        //��t������B
        if (l_strQueryString != null)
        {
            l_sbQueryString.append(l_strQueryString);
        }

        //�P�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA
        //��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B
        //������ID�A�⏕����ID�́A�����̕⏕�����I�u�W�F�N�g���ݒ肷��B
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(new Long(l_subAccount.getAccountId()));
        l_lisArrayList.add(new Long(l_subAccount.getSubAccountId()));
        l_lisArrayList.add(new Long(ProductTypeEnum.BOND.intValue()));
        l_lisArrayList.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        l_lisArrayList.add(new Long(0));

        Object[] l_objNewQueryContainers = null;
        if (l_objQueryContainers != null && l_objQueryContainers.length > 0)
        {
            for (int i = 0; i < l_objQueryContainers.length; i++)
            {
                l_lisArrayList.add(l_objQueryContainers[i]);
            }
            l_objNewQueryContainers = new Object[5 + l_objQueryContainers.length];
            l_lisArrayList.toArray(l_objNewQueryContainers);
        }
        else
        {
            l_objNewQueryContainers = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objNewQueryContainers);
        }

        //�R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ۗL���Y�I�u�W�F�N�g��List���擾����B
        //�@@�@@�@@doFindAllQuery(,�ۗL���YRow.TYPE
        //          �Q�|�P�j�̌�������������,
        //          �����̃\�[�g����,
        //          null,
        //          �Q�|�Q�j�̌��������f�[�^�R���e�i)
        List l_lisAssets = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisAssets = l_qp.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objNewQueryContainers);
        }
        catch (DataException l_ex)
        {
            log.error("__error in ���ۗL���Y�I�u�W�F�N�g�̎擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@�������ʂ��ۗL���Y�I�u�W�F�N�g�𐶐����A
        //List�Ƃ��ĕԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisAssets;
    }

    /**
     * (get�ۗL���Y)<BR>
     * get�ۗL���Y <BR>
     * <BR>
     * �P�jasset�e�[�u������ȉ��̏����ɊY������ۗL���Y���擾���� <BR>
     * ����ID=����.����ID <BR>
     * �⏕����ID=����.�⏕����ID<BR>
     * ����ID=����.����ID  <BR>
     * �ŋ敪=����.�ŋ敪 <BR>
     * �~�j���敪=DEFAULT�i�~�j���ȊO�j
     * <BR>
     * �Q�j�@@asset�e�[�u���̌������ʂ��ȉ��̂悤�ɖ߂� <BR>
     * <BR>
     * �ۗL���Y=null  <BR>
     * (��������List != null && ! ��������List.isEmpty())�̏ꍇ�A <BR>
     * { <BR>
     *  �ۗL���Y = new BondAssetImpl((AssetRow)��������List.get(0)) <BR>
     * } <BR>
     * <BR>
     * return �ۗL���Y <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngSubAccountId - (�⏕����ID)<BR>
     * �⏕����ID<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF103CC
     */
    public Asset getAsset(long l_lngAccountId,
        long l_lngSubAccountId, long l_lngProductId,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAsset(long, long, long, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        List l_lisAssets = null;

        //�P�jasset�e�[�u������ȉ��̏����ɊY������ۗL���Y���擾����
        //����ID=����.����ID
        //�⏕����ID=����.�⏕����ID
        //����ID=����.����ID
        //�ŋ敪=����.�ŋ敪
        //�~�j���敪=DEFAULT�i�~�j���ȊO�j
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" account_id = ?");
        l_sbQueryString.append(" and sub_account_id = ?");
        l_sbQueryString.append(" and product_id = ?");
        l_sbQueryString.append(" and tax_type = ?");
        l_sbQueryString.append(" and mini_stock_div = ? ");

        Object[] l_objQueries =
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                l_taxType,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK};
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisAssets = l_qp.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQueryString.toString(),
                null,
                l_objQueries);
        }
        catch (DataException l_ex)
        {
            log.error("__error in ���ۗL���Y�I�u�W�F�N�g�̎擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�@@asset�e�[�u���̌������ʂ��ȉ��̂悤�ɖ߂�
        //
        //�ۗL���Y=null
        //(��������List != null && ! ��������List.isEmpty())�̏ꍇ�A
        //{
        // �ۗL���Y = new BondAssetImpl((AssetRow)��������List.get(0))
        //}
        Asset l_asset = null;
        if (l_lisAssets != null && !l_lisAssets.isEmpty())
        {
            if (l_lisAssets.size() > 1)
            {
                log.debug("�f�[�^�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_asset = new BondAssetImpl((AssetRow)l_lisAssets.get(0));
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }

    /**
     * (update���b�N������)<BR>
     * <BR>
     * �y���b�N���̎��Y�ڍ׃e�[�u���z�̃��b�N�����ʂ̒������s���B   <BR>
     *�iupdateLockedQuantity(long accountId,   <BR>
     * long subAccountId,  <BR>
     * long orderUnitId,  <BR>
     * long productId,   <BR>
     * double lockedQtyToBeAdjusted)   <BR>
     * �̃I�[�o�[���C�h�j <BR>
     *   <BR>
     * �P�j�@@�ۗL���Y�`�F�b�N�敪���`�F�b�N<BR>
     * �@@�P�|�P�j�����X�ʏ����𐶐�����B<BR>
     *     �@@�@@�@@�@@[����] <BR>
     *     �@@�@@ �@@�@@���XID�F�g���A�J�E���g�}�l�[�W��.get�ڋq(����.����ID).get���X().get���XID() <BR>
     * �@@�P�|�Q�j�����X�ʏ���.get�ۗL���Y�`�F�b�N�敪 == '�`�F�b�N���Ȃ�'�̏ꍇ�A������������return����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ̍X�V�@@�@@<BR>
     * �@@�Q�|�P�j�g���������P�ʂ��擾����B�@@�@@<BR>
     *     �@@�@@�@@�@@[����]�@@�@@<BR>
     *     �@@�@@ �@@�@@�����P��ID�F�g���������}�l�[�W��.get�������P�ʁi����.�����P��ID�j�@@�@@<BR>
     * �@@�Q�|�Q�j�������ʂ��X�V����B�@@�@@<BR>
     * �@@�@@�@@�@@�@@�E�g���������P��.�������敪�����ρ@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ʁ��g���������P��.�������ʁ@@�~ -1�@@�@@<BR>
     * �@@�@@�@@�@@�@@�E�g���������P��.�������敪�������@@���@@�g���������P��.������ԁ������ρi�V�K�����j�@@���@@�g���������P��.���ŏI�ʔԁ�0�@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ʁ��g���������P��.�������ʁ@@�@@<BR>
     * �@@�@@�@@�@@�@@�E�g���������P��.�������敪������ρ@@���@@�g���������P��.���ŏI�ʔԁ�0�@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ʁ��g���������P��.�������ʁ@@�~ -1�@@�@@<BR>
     *    <BR>
     *    �R�j����.�������ʂ�0�Ɠ������ꍇ�́A������������return����B   <BR>
     *    <BR>
     *    �S�jthis.get�ۗL���Y(����ID, �⏕����ID, ����ID, �ŋ敪)���R�[�����A <BR>
     *        �ۗL���Y�I�u�W�F�N�g���擾����B <BR>
     *�@@[����] <BR>
     *�@@�@@����ID�@@�@@�@@�F����.����ID <BR>
     *�@@�@@�⏕����ID�F����.�⏕����ID <BR>
     *�@@�@@����ID�@@�@@�@@�F����.����ID <BR>
     *�@@�@@�ŋ敪�@@�@@�@@�F�������P�ʃI�u�W�F�N�g.get�ŋ敪 <BR>
     *     <BR>
     *    �T)�ۗL���Y�I�u�W�F�N�g.���YID���L�[�ɂ��āALocked_Asset_Details�e�[�u������������B <BR>
     *        LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(���YId);  <BR>
     *     <BR>
     *    �@@�T-�P)�@@ �Y�����YID��LockedAssetDetailsRow�����݂��Ȃ��ꍇ�AInsert���s���B   <BR>
     *        �|�V�K���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setAssetId(���YId);   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setAccountId(����.����ID);   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.�⏕����ID);   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setCreatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *        �@@�@@�V�K���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i����.�������ʁj;   <BR>
     *        �|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɐV�K�s��ǉ�����B   <BR>
     *        �@@�@@QueryProcessor qp = Processors.getDefaultProcessor();   <BR>
     *        �@@�@@qp.doInsertQuery(�V�K���b�N�����ʃI�u�W�F�N�g);   <BR>
     *     <BR>
     *        �T-�Q)�@@ �Y�����YID��LockedAssetDetailsRow�����łɑ��݂����ꍇ�AUpdate���s���B  <BR>
     *        �|�V���b�N�����ʂ��Z�o����B   <BR>
     *        �@@�@@�V���b�N������=lockedAssetRow.���b�N������ + ����.��������  <BR>
     *        �|�Z�o�������ʂ̐V���b�N�����ʂ��[����������B�i0�Ɠ������ꍇ�A0.0D���Z�b�g����j   <BR>
     *        �@@�@@(GtlUtils.Double.isZero(�V���b�N������))�̏ꍇ�A  <BR>
     *        �@@�@@�V���b�N������=0.0D;   <BR>
     *     <BR>
     *    �@@�|�X�V���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B   <BR>
     *    �@@�@@�@@�X�V���b�N�����ʃI�u�W�F�N�g.setAssetId(���YId);   <BR>
     *    �@@�@@�@@�X�V���b�N�����ʃI�u�W�F�N�g.setAccountId(����.����ID);   <BR>
     *    �@@�@@�@@�X�V���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.�⏕����ID);   <BR>
     *    �@@�@@�@@�X�V���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *    �@@�@@�@@�X�V���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i�V���b�N�����ʁj;   <BR>
     *    �@@�|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɊY���s���X�V����B   <BR>
     *        �@@�@@QueryProcessor qp = Processors.getDefaultProcessor();�@@   <BR>
     *        �@@�@@qp.doUpdateQuery(�X�V���b�N�����ʃI�u�W�F�N�g);     <BR>
     *�@@<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngSubAccountId - (�⏕����ID)<BR>
     * �⏕����ID<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dblLockedQtyToBeAdjusted - (��������)<BR>
     * ��������<BR>
     * @@roseuid 44D992BB02DE
     */
    public void updateLockedQuantity(long l_lngAccountId,
        long l_lngSubAccountId,
        long l_lngOrderUnitId,
        long l_lngProductId,
        double l_dblLockedQtyToBeAdjusted)
    {
        final String STR_METHOD_NAME = " updateLockedQuantity(long, long, long, long, double)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�ۗL���Y�`�F�b�N�敪���`�F�b�N
        //�g���A�J�E���g�}�l�[�W���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            // �P�|�P�j�����X�ʏ����𐶐�����B
            //    [����]
            //    ���XID�F�g���A�J�E���g�}�l�[�W��.get�ڋq(����.����ID).get���X().get���XID()
            long l_lngBranch =
                l_web3GentradeAccountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
            WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranch);

            // �P�|�Q�j�����X�ʏ���.get�ۗL���Y�`�F�b�N�敪 == '�`�F�b�N���Ȃ�'�̏ꍇ�A������������return����B
            if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in �g���A�J�E���g�}�l�[�W������ڋq���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in ���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j  �������ʂ̍X�V
        //  �Q�|�P�j�g���������P�ʂ��擾����B
        //            [����]
        //             �����P��ID�F�g���������}�l�[�W��.get�������P�ʁi����.�����P��ID�j
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        BondOrderUnit l_orderUnit =
            (BondOrderUnit) l_bondOrderManager.getOrderUnit(l_lngOrderUnitId);
        
        //  �Q�|�Q�j�������ʂ��X�V����B
        //          �E�g���������P��.�������敪������
        //              �������ʁ��g���������P��.��������  �~ -1
        //          �E�g���������P��.�������敪�������  ����  �g���������P��.������ԁ������ρi�V�K�����j�@@���@@�g���������P��.���ŏI�ʔԁ�0
        //              �������ʁ��g���������P��.��������
        //          �E�g���������P��.�������敪�������  ����  �g���������P��.���ŏI�ʔԁ�0
        //              �������ʁ��g���������P��.��������  �~ -1
        BondOrderUnitRow l_row = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderExecStatus = l_row.getOrderExecStatus();
        int l_intLastExecutionSerialNo = l_row.getLastExecutionSerialNo();
        
        //���
        if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity() * -1D;
        }
        //����̖����
        else if((WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus))
                && (OrderStatusEnum.ORDERED.equals(l_row.getOrderStatus()))
                && (l_intLastExecutionSerialNo > 0))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity();
        }
        //����̎����t
        else if((WEB3BondOrderExecStatusDef.CANCELED.equals(l_strOrderExecStatus))
                && (l_intLastExecutionSerialNo > 0))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity() * -1D;
        }
        
        //�R�j�@@����.�������ʂ�0�Ɠ������ꍇ�́A������������return����B
        if (l_dblLockedQtyToBeAdjusted == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        try
        {
            //�S�j�@@this.get�ۗL���Y(����ID, �⏕����ID, ����ID, �ŋ敪)���R�[�����A
            //�ۗL���Y�I�u�W�F�N�g���擾����B
            //[����]
            //  ����ID�@@�@@�@@�F����.����ID
            //  �⏕����ID�F����.�⏕����ID
            //  ����ID�@@�@@�@@�F����.����ID
            //  �ŋ敪�@@�@@�@@�F�������P�ʃI�u�W�F�N�g.get�ŋ敪
            Asset l_asset = null;
            if (l_orderUnit != null)
            {
                l_asset = this.getAsset(
                    l_lngAccountId, l_lngSubAccountId, l_lngProductId, l_orderUnit.getTaxType());
            }

            //�T)�@@�ۗL���Y�I�u�W�F�N�g.���YID���L�[�ɂ��āALocked_Asset_Details�e�[�u������������B
            //LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(���YId);
            LockedAssetDetailsRow l_lockedAssetRow = null;
            LockedAssetDetailsParams l_lockedAssetParams = null;
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            if (l_asset != null)
            {
                l_lockedAssetRow = LockedAssetDetailsDao.findRowByAssetId(l_asset.getAssetId());

                //�T-�P)�@@ �Y�����YID��LockedAssetDetailsRow�����݂��Ȃ��ꍇ�AInsert���s���B
                if (l_lockedAssetRow == null)
                {
                    l_lockedAssetParams = new LockedAssetDetailsParams();

                    // �|�V�K���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B               
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setAssetId(���YId);  
                    l_lockedAssetParams.setAssetId(l_asset.getAssetId());
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setAccountId(����.����ID);
                    l_lockedAssetParams.setAccountId(l_lngAccountId);
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.�⏕����ID);
                    l_lockedAssetParams.setSubAccountId(l_lngSubAccountId);
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    //    �V�K���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i����.�������ʁj;
                    l_lockedAssetParams.setLockedQuantity(l_dblLockedQtyToBeAdjusted);

                    //   �@@�|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɐV�K�s��ǉ�����B
                    //   �@@�@@�@@QueryProcessor qp = Processors.getDefaultProcessor();
                    //   �@@�@@�@@qp.doInsertQuery(�V�K���b�N�����ʃI�u�W�F�N�g);
                    l_qp.doInsertQuery(l_lockedAssetParams);
                }
                else
                {
                    l_lockedAssetParams = new LockedAssetDetailsParams(l_lockedAssetRow);

                    //�T-�Q)�@@ �Y�����YID��LockedAssetDetailsRow�����łɑ��݂����ꍇ�AUpdate���s���B
                    //   �|�V���b�N�����ʂ��Z�o����B
                    //   �@@ �V���b�N������=lockedAssetRow.���b�N������ + ����.��������
                    BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_lockedAssetRow.getLockedQuantity()));
                    BigDecimal l_bdLockedQtyToBeAdjusted = new BigDecimal(String.valueOf(l_dblLockedQtyToBeAdjusted));
                    double l_dblNewQuantity =
                        l_bdLockedQuantity.add(l_bdLockedQtyToBeAdjusted).doubleValue() ;

                    //�|�Z�o�������ʂ̐V���b�N�����ʂ��[����������B�i0�Ɠ������ꍇ�A0.0D���Z�b�g����j
                    // (GtlUtils.Double.isZero(�V���b�N������))�̏ꍇ�A
                    if (GtlUtils.Double.isZero(l_dblNewQuantity))
                    {
                        //�V���b�N������=0.0D;
                        l_dblNewQuantity = 0.0D;
                    }
                    //�|�X�V���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B

                    //�X�V���b�N�����ʃI�u�W�F�N�g.setAccountId(����.����ID);
                    l_lockedAssetParams.setAccountId(l_lngAccountId);

                    //�X�V���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.�⏕����ID);
                    l_lockedAssetParams.setSubAccountId(l_lngSubAccountId);

                    //�X�V���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //�X�V���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i�V���b�N�����ʁj;
                    l_lockedAssetParams.setLockedQuantity(l_dblNewQuantity);

                    //�|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɊY���s���X�V����B
                    //   �@@�@@�@@QueryProcessor qp = Processors.getDefaultProcessor();�@@
                    //   �@@�@@�@@qp.doUpdateQuery(�X�V���b�N�����ʃI�u�W�F�N�g);
                    l_qp.doUpdateQuery(l_lockedAssetParams);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in DB conncetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("__error in DB conncetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
