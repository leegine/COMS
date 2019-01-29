head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ(WEB3PvInfoDataManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/04 ���Ō�(���u) �V�K�쐬
Revesion History : 2006/05/23 �юu��(���u) �d�l�ύX ���f��063
Revesion History : 2006/09/12 �����F(���u) �d�l�ύX���f��070
Revesion History : 2007/02/26 ����(���u) �d�l�ύX���f��073
Revesion History : 2007/03/16 ����(���u) �d�l�ύX���f��076
Revision History : 2007/07/13 �Ӑ�(���u) �d�l�ύX���f��083
Revision History : 2007/12/07 �И���(���u) �d�l�ύX���f��095,096
Revision History : 2008/10/06 �đo�g(���u) �d�l�ύX���f��106
*/
package webbroker3.pvinfo;

import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;


/**
 * (��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ)<BR>
 * ��ײ�ްĲ�̫Ұ��݂�DB I/O�Ȃǂ��Ǘ�����N���X�B(�C���^�t�F�C�X)<BR>
 * @@author ���Ō�
 * @@version 1.0
 */
public interface WEB3PvInfoDataManager extends Service
{
   
   /**
    * (get��������)<BR>
    * �����̏��i�敪�̒����������擾����B<BR>
    * @@param l_strProductDiv - ���i�敪<BR>
    * <BR>
    * 0�F�@@���� <BR>
    * 1�F�@@�M�p <BR>
    * 2�F�@@�敨 <BR>
    * 3�F�@@�I�v�V���� <BR>
    * 
    * @@param l_isTodayOrder - (is��������)<BR>
    * �����������擾���邩�ǂ����̃t���O<BR>
    * <BR>
    * false�F�@@��������<BR>
    * true�F�@@��������<BR>
    * @@param l_strQueryString - ��������������
    * @@param l_strQueryDataContainers - ���������f�[�^�R���e�i
    * @@return int
    * @@roseuid 4141331503A3
    */
   public int getOrderCnt(String l_strProductDiv, boolean l_isTodayOrder, String l_strQueryString, String[] l_strQueryDataContainers) 
       throws WEB3BaseException;
   
   /**
    * (get��茏��)<BR>
    * �����̏��i�敪�̓�����茏�����擾����B<BR>
    * @@param l_mainAccount - (�ڋq)<BR>
    * �ڋq�I�u�W�F�N�g<BR>
    * @@param l_strProductDiv - ���i�敪<BR>
    * <BR>
    * 0�F�@@���� <BR>
    * 1�F�@@�M�p <BR>
    * 2�F�@@�敨 <BR>
    * 3�F�@@�I�v�V���� <BR>
    * 
    * @@return int
    * @@roseuid 414152F00384
    */
   public int getExecuteCnt(WEB3GentradeMainAccount l_mainAccount, String l_strProductDiv)
       throws WEB3BaseException;
   
   /**
    * (get�\�����eParams)<BR>
    * �����̕\�����eID�ɊY������\�����eParams��<BR>
    * �\�����e�e�[�u������擾����B<BR>
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@return DisplayContentsParams
    * @@roseuid 4147A21F0069
    */
   public DisplayContentsParams getDisplayContentsParams(long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get�\�����eParams�ꗗ)<BR>
    * �����ɊY������\�����eParams�̈ꗗ��<BR>
    * �\�����e�e�[�u������擾����B<BR>
    * @@param l_strQueryString - ��������������
    * @@param l_strQueryDataContainers - ���������f�[�^�R���e�i
    * @@param l_strSortCond - �\�[�g����
    * @@return List
    * @@roseuid 4145092300D9
    */
   public List getDisplayContentsParamsList(String l_strQueryString, String[] l_strQueryDataContainers, String l_strSortCond)
       throws WEB3BaseException;
   
   /**
    * (insert�\�����e)<BR>
    * �\�����e�e�[�u���ɐV�K�f�[�^����s�o�^����B<BR>
    * @@param l_administrator - (�Ǘ���)<BR>
    * �Ǘ��҃I�u�W�F�N�g<BR>
    * @@param l_displayContentsInfo - (�\�����e���)<BR>
    * �\�����e���I�u�W�F�N�g<BR>
    * @@roseuid 415BF49B0047
    */
   public void insertDisplayContents(WEB3Administrator l_administrator, WEB3PvInfoDisplayContentsUnit l_displayContentsInfo)
       throws WEB3BaseException;
   
   /**
    * (update�\�����e)<BR>
    * �����̕\�����eParams�ŕ\�����e�e�[�u�����X�V����B<BR>
    * @@param l_displayContentsParams - (�\�����eParams)<BR>
    * �\�����eParams�I�u�W�F�N�g<BR>
    * @@roseuid 415BF49B0076
    */
   public void updateDisplayContents(DisplayContentsParams l_displayContentsParams)
       throws WEB3BaseException;
   
   /**
    * (delete�\�����e)<BR>
    * �����̕\�����eID�ɊY������\�����e�e�[�u���̃f�[�^���폜����B<BR>
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@roseuid 415D3043036B
    */
   public void deleteDisplayContents(long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get�V�K�\�����eID)<BR>
    * �\�����eID��V�K�̔Ԃ��A�ԋp����B<BR>
    * @@return long
    * @@roseuid 415C0F010281
    */
   public long getNewDisplayContentsId()
       throws WEB3BaseException;
   
   /**
    * (get�\�������ݒ�Params�ꗗ)<BR>
    * �����ɊY������\�������ݒ�Params�̈ꗗ���擾����B<BR>
    * @@param l_strInstitutionCode - �،���ЃR�[�h
    * @@param l_strBranchCode - ���X�R�[�h
    * @@return List
    * @@roseuid 415BE56D02E2
    */
   public List getDisplayConditionParamsList(String l_strInstitutionCode, String l_strBranchCode)
       throws WEB3BaseException;
   
   /**
    * (create�\���������ꗗ)<BR>
    * �_�C���N�g�w��������A�\���������̈ꗗ���쐬����B<BR>
    * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
    * @@return WEB3PvInfoDisplayConditionUnit[]
    * @@roseuid 415BFC23009C
    */
   public WEB3PvInfoDisplayConditionUnit[] createDisplayConditionList(WEB3Administrator l_administrator)
       throws WEB3BaseException;
   
   /**
    * (get�{������Params)<BR>
    * �{������Params���擾����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@return BrowseHistoryParams
    * @@roseuid 414523FA032B
    */
   public BrowseHistoryParams getBrowseHistoryParams(WEB3GentradeMainAccount l_mainAccount, long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get�{������Params�ꗗ)<BR>
    * �����ɊY������{������Params�̈ꗗ��<BR>
    * �\�����e�e�[�u������擾����B<BR>
    * @@param l_strQueryString - ��������������
    * @@param l_strQueryDataContainers - ���������f�[�^�R���e�i
    * @@param l_strSortCond - �\�[�g����
    * @@return List
    * @@roseuid 415CBDF403C1
    */
   public List getBrowseHistoryParamsList(String l_strQueryString, String[] l_strQueryDataContainers, String l_strSortCond)
       throws WEB3BaseException;
   
   /**
    * (insert�{������)<BR>
    * �{�������e�[�u���Ƀf�[�^����s�o�^����B<BR>
    * @@param l_strInstitutionCode - �،���ЃR�[�h
    * @@param l_strBranchCode - ���X�R�[�h
    * @@param l_strAccountCode - ���X�R�[�h
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@param l_isRead - ���Ǌ��ǃt���O�����ǂœo�^���邩�ǂ����̃t���O<BR>
    * <BR>
    * false�F�@@���ǂœo�^<BR>
    * true�F�@@���ǂœo�^<BR>
    * @@roseuid 4160D8C4017D
    */
   public void insertBrowseHistory(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId, boolean l_isRead)
       throws WEB3BaseException;
   
   /**
    * (update�{������)<BR>
    * �����̉{������Params�ŉ{�������e�[�u�����X�V����B<BR>
    * @@param l_browseHistoryParams - �{������Params�I�u�W�F�N�g
    * @@roseuid 4145236D005C
    */
   public void updateBrowseHistory(BrowseHistoryParams l_browseHistoryParams)
       throws WEB3BaseException;
   
   /**
    * (delete�{������)<BR>
    * �����̕\�����eID�ɊY������{�������e�[�u���̃f�[�^���폜����B<BR>
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@roseuid 4160E9200090
    */
   public void deleteBrowseHistory(long l_lngDisplayContentsId)
   throws WEB3BaseException;
   
   /**
    * (delete�{������)<BR>
    * �����̌ڋq���A�\�����eID�ɊY������{�������e�[�u���̃f�[�^���폜����B<BR>
    * @@param l_strInstitutionCode - �،���ЃR�[�h
    * @@param l_strBranchCode - ���X�R�[�h
    * @@param l_strAccountCode - �ڋq�R�[�h
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@roseuid 41610CD7028C
    */
   public void deleteBrowseHistory(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId)
   throws WEB3BaseException;
   
   /**
    * (getIPO�\��Params)<BR>
    * ���I����IPO�\��Params���擾����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@return List
    * @@roseuid 41454EEC000E
    */
   public List getIpoOrderParams(WEB3GentradeMainAccount l_mainAccount)
       throws WEB3BaseException;
   
   /**
    * (getIPO����Params)<BR>
    * ������IPO����ID�ɊY������w���\�����ؑO��<BR>
    * IPO����Params���擾����B<BR>
    * @@param l_lngIpoProductId - IPO����ID
    * @@param l_isAdvancedElection - is�J�グ���I
    * @@return IpoProductParams
    * @@roseuid 41455DCC02AE
    */
   public IpoProductParams getIpoProductParams(long l_lngIpoProductId, boolean l_isAdvancedElection)
       throws WEB3BaseException;
   
   /**
    * (get���ϊ����ԋߌ��ʈꗗ)<BR>
    * �����̌ڋq�̕ێ����錈�ϊ����ԋ߂̌��ʂ��擾����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@param l_isSettleBeforeOneWeek - (is���ψ�T�ԑO)<BR>
    * ���ϊ�������T�ԑO���ǂ����̃t���O<BR>
    * <BR>
    * false�F�@@���ψꃖ���O<BR>
    * true�F�@@���ψ�T�ԑO<BR>
    * @@return List
    * @@roseuid 414567D50221
    */
   public List getSettleContractList(WEB3GentradeMainAccount l_mainAccount, boolean l_isSettleBeforeOneWeek)
       throws WEB3BaseException;
   
   /**
    * (get�؋����s���ꗗ)<BR>
    * �����̌ڋq�ɊY������f�[�^���؋����e�[�u������擾����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@return List
    * @@roseuid 4145913501B4
    */
   public List getIfoDepositShortageList(WEB3GentradeMainAccount l_mainAccount)
       throws WEB3BaseException;
   
   /**
    * (get��������Params)<BR>
    * �����̖���ID�ɊY�����銔������Params��<BR>
    * ���������e�[�u�����擾����B<BR>
    * @@param l_lngProductId - ����ID
    * @@return EqtypeProductParams
    * @@roseuid 4147AB310115
    */
   public EqtypeProductParams getEqtypeProductParams(long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (get�敨OP����Params)<BR>
    * �����̖���ID�ɊY������敨OP����Params��<BR>
    * �敨OP�����e�[�u�����擾����B<BR>
    * @@param l_lngProductId - ����ID
    * @@return IfoProductParams
    * @@roseuid 41593981026F
    */
   public IfoProductParams getIfoProductParams(long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (clone�{������Params)<BR>
    * �����̉{������Params���R�s�[���āA<BR>
    * �������e�̕ʃC���X�^���X���쐬���A�ԋp����B<BR>
    * @@param l_browseHistoryParams - �{������Params�I�u�W�F�N�g
    * @@return BrowseHistoryParams
    * @@roseuid 4147C8E403AD
    */
   public BrowseHistoryParams cloneBrowseHistoryParams(BrowseHistoryParams l_browseHistoryParams);
   
   /**
    * (clone�\�����eParams)<BR>
    * �����̕\�����eParams���R�s�[���āA<BR>
    * �������e�̕ʃC���X�^���X���쐬���A�ԋp����B<BR>
    * @@param l_displayContentsParams - �\�����eParams�I�u�W�F�N�g
    * @@return DisplayContentsParams
    * @@roseuid 4147C96201B9
    */
   public DisplayContentsParams cloneDisplayContentsParams(DisplayContentsParams l_displayContentsParams);
   
   /**
    * (create�\�����eParams)<BR>
    * �����̕\�����e��񂩂�<BR>
    * �\�����eParams���쐬���A�ԋp����B<BR>
    * @@param l_displayContentsUnit - �\�����e���I�u�W�F�N�g
    * @@return DisplayContentsParams
    * @@roseuid 415D227A02CF
    */
   public DisplayContentsParams createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
       throws WEB3BaseException;
   
   /**
    * (is���Y�ۗL)<BR>
    * �����̖����^�C�v�ɊY�����鎑�Y��ۗL���Ă��邩���ʂ���B<BR>
    * <BR>
    * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@param l_productType - �����^�C�v<BR>
    * (ProductTypeEnum�ɂĒ�`)<BR>
    * @@param l_isMiniStock - (is�~�j��)<BR>
    * �擾�ΏەۗL���Y���A�~�j�����ǂ����̃t���O<BR>
    * <BR>
    * true�F�@@�~�j��<BR>
    * false�F�@@�~�j���łȂ�<BR>
    * @@return boolean
    * @@roseuid 41590F7E0221
    */
   public boolean isAssetHas(WEB3GentradeMainAccount l_mainAccount, ProductTypeEnum l_productType, boolean l_isMiniStock)
       throws WEB3BaseException;
   
   /**
    * (is���ʕۗL)<BR>
    * �����̖����^�C�v�A�敨�^�I�v�V�����敪�ɊY�����錚�ʂ�<BR>
    * �ۗL���Ă��邩���ʂ���B<BR>
    * <BR>
    * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR>
    * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
    * @@param l_productType - �����^�C�v<BR>
    * (ProductTypeEnum�ɂĒ�`)<BR>
    * @@param l_strFutureOptionDiv - �敨�^�I�v�V�����敪<BR>
    * <BR>
    * 0�F�@@DEFAULT<BR>
    * 1�F�@@�敨<BR>
    * 2�F�@@�I�v�V����<BR>
    * @@return boolean
    * @@roseuid 4159148800B9
    */
   public boolean isContractHas(WEB3GentradeMainAccount l_mainAccount, ProductTypeEnum l_productType, String l_strFutureOptionDiv)
       throws WEB3BaseException;
   
   /**
    * (get�{������Params�ꗗ)<BR>
    * �����̏����ɊY������{������Params�̈ꗗ���擾����B<BR>
    * @@param l_strInstitutionCode - �،���ЃR�[�h
    * @@param l_strBranchCode - ���X�R�[�h�̔z��
    * @@param l_strAccountCode - �ڋq�R�[�h
    * @@param l_lngDisplayContentsId - �\�����eID
    * @@return List
    * @@roseuid 4214199602DB
    */
   public List getBrowseHistoryParamsList(String l_strInstitutionCode, String[] l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get�c�Ɠ��ꗗ)<BR>
    * �c�Ɠ��̈ꗗ��ԋp����B<BR>
    * @@return Date[]
    * @@roseuid 4214360C0274
    */
   public Date[] getBizDateList()
       throws WEB3BaseException;
   
   /**
    * (get�������)<BR>
    * �����̏��i�敪�̔���������v���擾����B<BR>
    * <BR>
    * �������敪���킸�P�� * ���ʂ̐�Βl�̏W�v���s���B<BR>
    * �@@�萔���A����ł͊܂܂�Ȃ��B<BR>
    * �@@�����E���n�̑���͉��Z����Ȃ��B<BR>
    * @@param l_strProductDiv - ���i�敪<BR>
    * <BR>
    * 0�F�@@���� <BR>
    * 1�F�@@�M�p <BR>
    * 2�F�@@�敨 <BR>
    * 3�F�@@�I�v�V���� <BR>
    * 
    * @@param l_strQueryString - ��������������
    * @@param l_strQueryDataContainers - ���������f�[�^�R���e�i
    * @@return double
    * @@roseuid 421451A202C5
    */
   public double getTradePrice(String l_strProductDiv, String l_strQueryString, String[] l_strQueryDataContainers)
       throws WEB3BaseException;
   
   /**
    * (get�g�����U�N�V�����ꗗ)<BR>
    * �g�����U�N�V�����̈ꗗ���擾����B<BR>
    * @@param rowType - RowType
    * @@param l_lngOrderId - ����ID
    * @@param l_lngOrderUnitId - �����P��ID
    * @@return List
    * @@roseuid 4214692F0120
    */
   public List getFinTransactionList(RowType rowType, long l_lngOrderId, long l_lngOrderUnitId)
       throws WEB3BaseException;
   
   /**
    * (get�w���搔)<BR>
    * �w���搔���擾���ԋp����B<BR>
    * @@param l_lngBranchId - ���XID
    * @@param l_lngMarketId - �s��ID
    * @@param l_lngProductId - ����ID
    * @@return double
    * @@roseuid 42146A8F013B
    */
   public double getUnitSize(long l_lngBranchId, long l_lngMarketId, long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (get���Y�]�͏��)<BR>
    * �����̕⏕�����ɊY�����鎑�Y�]�͏���ԋp����B<BR>
    * <BR>
    * �߂�l�F�@@���Y�]�͏��<�����ڋq> or<BR>
    * �@@�@@�@@�@@�@@�@@���Y�]�͏��<�M�p�ڋq><BR>
    * <BR>
    * ���{���\�b�h���g�p����ꍇ�́A<BR>
    * �g�p����T�[�r�X�̃T�[�r�X�C���^�Z�v�^.onReturn()�AonThrowable()���ɂāA<BR>
    * "TRADING_POWER_INFO"�̐ݒ�L�[�ɂĐݒ肳��Ă���f�[�^���N���A���邱�ƁB<BR>
    * @@param l_subAccount - �⏕�����I�u�W�F�N�g
    * @@return Object
    * @@roseuid 4215A73A00FF
    */
   public Object getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (get��������������)<BR>
    * ����������������ԋp����B<BR>
    * @@param l_subAccount - �⏕�����I�u�W�F�N�g
    * @@return Date
    * @@roseuid 4215AAE60238
    */
   public Date getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (get���֋�������)<BR>
    * ���֋���������ԋp����B<BR>
    * @@param l_subAccount - �⏕�����I�u�W�F�N�g
    * @@return Date
    * @@roseuid 4215B4A5016D
    */
   public Date getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (is���ʋ�������)<BR>
    * ���������̑ΏۂƂȂ錚�ʂ�<BR>  
    * �ۗL���Ă��邩���ʂ���B<BR>  
    * <BR>  
    * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR>
    * <BR>
    * @@param l_mainAccount - (�ڋq)
    * �ڋq�I�u�W�F�N�g<BR>
    * @@param l_strConditionNo - (�\�������ԍ�)
    * �\�������ݒ�Params.�\�������ԍ�<BR>
    * @@return boolean
    * @@throws WEB3BaseException 
    */
   public boolean  isContractEnforcedDisposal(
       WEB3GentradeMainAccount l_mainAccount, String l_strConditionNo) throws WEB3BaseException;
   
   /**
    * (get���������Ǘ��M�pParams)<BR>
    * ���������Ǘ��M�pParams���擾����B<BR> 
    * <BR>
    * @@param l_mainAccount - (�ڋq)
    * �ڋq�I�u�W�F�N�g<BR>
    * @@return PaymentRequisitionMarginParams 
    * @@throws WEB3BaseException 
    */
   public PaymentRequisitionMarginParams getPaymentRequisitionMarginParams(
       WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
   
   /**
    * (is�o����~)<BR>
    *�o����~�ڋq�����ʂ���B<BR>                 
    * <BR>                                        
    *�o����~�ڋq�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR> 
    *@@param l_mainAccount-(�ڋq)<BR>
    *�ڋq�I�u�W�F�N�g<BR>
    *@@param l_strCashoutStopDiv-�o����~�敪 <BR>
    *�o����~�敪<BR>
    *<BR>
    *1�F�S�z<BR>
    *2�F�ꕔ<BR>
    *@@return boolean 
    *@@throws WEB3BaseException
    */
   public boolean isCashoutStop(WEB3GentradeMainAccount l_mainAccount,
       String l_strCashoutStopDiv)throws WEB3BaseException;
   
   /**
    * (get�S�ەs���ڋq�f�[�^Params)<BR>
    *�����ɊY������S�ەs���ڋq�f�[�^Params���擾����B<BR> >
    *@@param l_mainAccount-(�ڋq)<BR>
    *�ڋq�I�u�W�F�N�g<BR>
    *@@param l_strCashoutStopDiv-�o����~�敪 <BR>
    *�o����~�敪<BR>
    *<BR>
    *1�F�S�z<BR>
    *2�F�ꕔ<BR>
    *@@return �S�ەs���ڋq�f�[�^Params  
    *@@throws WEB3BaseException
    */
   public SecurityShortageAccountParams getSecurityShortageAccountParams(
       WEB3GentradeMainAccount l_mainAccount,
       String l_strCashoutStopDiv)throws WEB3BaseException;
   
   /**
	 * (is�萔�������L�����y�[��)<BR>
	 * �萔�������L�����y�[���Ώیڋq�����ʂ���B<BR>
	 * <BR>
	 * �萔�������L�����y�[���Ώیڋq�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@param l_mainAccount-(�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount) 
	    throws WEB3BaseException;

	/**
	 * (get�����L�����y�[���ڋqParams)<BR>
	 * �萔�������L�����y�[���ڋq����Params���擾����B<BR>
     * @@param l_mainAccount-(�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR> 
	 * @@return CommCampaignAccHistoryParams[]
	 * @@throws WEB3BaseException
	 */
	public CommCampaignAccHistoryParams[] getCommCampaignAccHistoryParams(
	   WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

	/**
	 * (get���i�R�[�h)<BR>
	 * �萔�������L�����y�[�����i�}�X�^���珤�i�R�[�h���擾����B<BR>
	 * @@param l_lngAccInfoConditionId-(�L�����y�[���萔������ID)<BR>
	 * @@return String[]
	 * @@throws WEB3BaseException 
	 */
	public String[] getCommProductCodes(long l_lngAccInfoConditionId) throws WEB3BaseException;

    /**
     * (is���o�C����p�����J��)<BR>
     * ���o�C����p�����̊J�݁^���J�݂𔻕ʂ���B  <BR>
     * <BR>
     * ���o�C����p�����J�݂̏ꍇtrue�A�ȊO(���o�C����p�������J��)�̏ꍇfalse��ԋp����B <BR>
     * <BR>
     * @@param l_mainAccount - WEB3GentradeMainAccount
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOnlyMobileOpen(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (is���ʌ�t�����11�����o��)<BR>
     * ���ʌ�t�����11�����o�߂̌ڋq�����ʂ���B <BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeliveryDate(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (get���ʌ�t�Ǘ�Params)<BR>
     * ���������ɊY�����鏑�ʌ�t�Ǘ����R�[�h��List�^�ŕԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDeliveryManagementParams(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�s����������)<BR>
     * �Y���ڋq�̕s���������󋵂�Ԃ��B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@�s����������<BR>
     * "1"�F�@@�s���������i�M�p�������J�݁j<BR>
     * "2"�F�@@�s���������i�M�p�����J�݁j<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getShortfallGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�Ǐؔ�����)<BR>
     * �Y���ڋq�̕s���������󋵂�Ԃ��B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@�Ǐؖ�����<BR>
     * "1"�F�@@��ꐅ���Ǐؔ���<BR>
     * "2"�F�@@��񐅏��Ǐؔ���<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdddepositGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�s�����������)<BR>
     * �Y���ڋq�̕s������������Ԃ��B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3TPShortfallOccurInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�Ǐؔ������)<BR>
     * �Y���ڋq�̕s������������Ԃ��B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�]�͎����~�敪)<BR>
     * �ڋq�]�͏����e�[�u���̎����~�敪���擾����B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@����\<BR>
     * "1"�F�@@�����~<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTPTradingStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get�ڋq�]�͏���Params)<BR>
     * ���������ɊY������ڋq�]�͏������R�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_accountId - (����ID)<BR>
     * ����ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    public TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException;

    /**
     * (get�Ǐؖ������敪)<BR>
     * �ڋq�]�͏����e�[�u���̒Ǐؖ������敪���擾����B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@�Ǐؖ������Ȃ��i����\�j<BR>
     * "1"�F�@@�Ǐؖ���������i�����~�j<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionalDepositStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;
}
@
