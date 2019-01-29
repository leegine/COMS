head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccDataGettingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������f�[�^�擾�T�[�r�XImpl(WEB3ToSuccDataGettingServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 �Г�(���u) �V�K�쐬
Revesion History : 2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
Revesion History : 2006/11/24 ���G��(���u) �d�l�ύX���f��185
Revesion History : 2006/11/30 ���G��(���u) �d�l�ύX���f��198 201
Revesion History : 2007/06/05 �đo�g(���u) �d�l�ύX���f��236
Revesion History : 2008/03/18 ��іQ(���u) �d�l�ύX���f��258, 288
Revesion History : 2008/04/09 ����(���u) �d�l�ύX���f��315
Revesion History : 2008/04/17 ��іQ(���u) �d�l�ύX���f��327
Revesion History : 2008/05/08 ��іQ(���u) �d�l�ύX���f��334
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccTransactionStateDef;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccReservationOrderUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.triggerorder.util.WEB3TriggerOrderUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�A�������f�[�^�擾�T�[�r�XImpl)<BR>
 * �A�������f�[�^�擾�T�[�r�X�����N���X<BR>
 * <BR>
 * �A�������@@�\�̋��ʏ�������������B<BR>
 * <BR>
 * @@author �Г� <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccDataGettingServiceImpl 
    implements WEB3ToSuccDataGettingService 
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B)�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToSuccDataGettingServiceImpl.class);

    /**
     * @@roseuid 4348DB900167
     */
    public WEB3ToSuccDataGettingServiceImpl() 
    {

    }

    /**
     * (create�A����������)<BR>
     * �����̘A���������ׂɃv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g����ȉ��̒l��<BR>
     * �@@�擾���A�ޔ�������B<BR>
     * �@@�@@���s��R�[�h<BR>
     * �@@�@@�������R�[�h<BR>
     * �@@�@@����t���ԋ敪<BR>
     * <BR>
     * �Q�j�@@�^�̔���<BR>
     * �@@�p�����[�^.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A<BR>
     * �@@�ȉ��̂����ꂩ�ɃL���X�g����B<BR>
     * �@@�E���������P��<BR>
     * �@@�E�敨OP�����P��<BR>
     * �@@�E�����\�񒍕��P��Impl<BR>
     * �@@�E�敨OP�\�񒍕��P��Impl<BR>
     * <BR>
     * �R�j�p�����[�^.�A���������ׂɈȉ��̃v���p�e�B��<BR>
     * �@@�Z�b�g����B<BR>
     * �@@�������P�ʂƂ��āA�L���X�g�������̂��g�p����B<BR>
     * �@@�@@�v���p�e�B�Z�b�g�ɕK�v�Ȓ����P�ʂ̊e�l�́A<BR>
     * �@@�@@�L���X�g�������_�ŕʕϐ��Ƃ��Ď擾���A<BR>
     * �@@�@@�v���p�e�B�Z�b�g�̃��W�b�N�͋��ʉ�����悤�Ɏ������邱�ƁB<BR>
     * <BR>
     * �@@����ID = �����P��.����ID<BR>
     * �@@���i�敪 = this.get���i�敪(�����P��.�������)<BR>
     * �@@�����R�[�h = ����(*1).�����R�[�h<BR>
     * �@@������ = ����(*1).������<BR>
     * �@@�w�����(*10) = ����.�����Y�����R�[�h<BR>
     * �@@����(*10) = ����.����<BR>
     * �@@�I�v�V�������i�敪(*3) =<BR>
     * �@@�@@[����.�敨�I�v�V�������i�敪 == "�v�b�g�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@"�v�b�g�I�v�V����"���Z�b�g�B<BR>
     * �@@�@@[����.�敨�I�v�V�������i�敪 == "�R�[���I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@"�R�[���I�v�V����"���Z�b�g�B<BR>
     * �@@�s�g���i(*3) = ����.�s�g���i<BR>
     * �@@�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�����敪(*7) = �����f�[�^�A�_�v�^.get�����敪(���� �����P��.getTaxType)<BR>
     * �@@����敪(*8) = �����P��.������ʁA����R�[�h�iSONAR�j���Z�b�g<BR>
     * �@@�ٍϋ敪(*5) = �����P��.�ٍϋ敪<BR>
     * �@@�ٍϊ����l(*5) = �����P��.�ٍϊ����l<BR>
     * �@@�l�i����(*4) = �����P��.�l�i����<BR>
     * �@@���s����(*6)=this.get���s�����iPR�w�j(�����P��)<BR>
     * �@@���������敪(*6)=�����P��.��������<BR>
     * <BR>
     * �@@[�����P��.��������=="�t�w�l"�̏ꍇ]<BR>
     * �@@�@@�t�w�l�p�v���~�A���^�����Y���i(*2)=�����P��.�t�w�l��l�^�C�v<BR>
     * �@@�@@�t�w�l�p���������P��(*6)=�����P��.�t�w�l��l<BR>
     * �@@�@@�t�w�l�p�����������Z�q(*6)=�����P��.�����������Z�q<BR>
     * �@@[�����P��.��������=="W�w�l"�̏ꍇ]<BR>
     * �@@�@@�v�w�l�p�v���~�A���^�����Y���i(*2)=�����P��.�t�w�l��l�^�C�v<BR>
     * �@@�@@�v�w�l�p���������P��(*6)=�����P��.�t�w�l��l<BR>
     * �@@�@@�v�w�l�p�����������Z�q(*6)=�����P��.�����������Z�q<BR>
     * �@@�@@�v�w�l�p�����P���敪(*6)=�����P��.�iW�w�l�j�����w�l==0�̏ꍇ�A"���s"�B<BR>
     * �@@�@@�@@�ȊO�A"�w�l"���Z�b�g�B<BR>
     * �@@�@@�v�w�l�p�����P��(*6)=�����P��.�iW�w�l�j�����w�l!=0�̏ꍇ�A���̒l���Z�b�g�B<BR>
     * <BR>
     * �@@���ȉ��A�u�f�[�^�A�_�v�^�v�͒����P�ʂ̌^�ɂ�莟�̂悤�ɓǂݑւ��鎖�B<BR>
     * �@@�@@���������P�ʂ̏ꍇ�A�u�����f�[�^�A�_�v�^�v<BR>
     * �@@�@@�敨OP�����P�ʂ̏ꍇ�A�u�敨OP�f�[�^�A�_�v�^�v<BR>
     * <BR>
     * �@@�v�w�l�p���s����(*6) = �f�[�^�A�_�v�^.get�v�w�l�p���s����(�����P��)�̖߂�l<BR>
     * �@@�v�w�l�p�L����ԋ敪(*6)=�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)<BR>
     * �@@�@@�̖߂�l<BR>
     * �@@�v�w�l�p�֑ؑO�����P��(*6)=�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)<BR>
     * �@@�@@�̖߂�l<BR>
     * �@@�v�w�l�p�֑ؑO���s����(*6)=�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)<BR>
     * �@@�@@�̖߂�l<BR>
     * �@@�����������敪(*6)=�����P��.����������<BR>
     * �@@�����������P��(*6)=�����P��.���t�w�l��l<BR>
     * �@@�������������Z�q(*6)=�����P��.�������������Z�q<BR>
     * �@@���v�w�l�p�����P���敪(*6)=�f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)<BR>
     * �@@�@@�̖߂�l<BR>
     * �@@���v�w�l�p�����P��(*6)=��W�w�l�p�����P���敪=="�w�l"�̏ꍇ�̂݁A<BR>
     * �@@�@@�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)�̖߂�l<BR>
     * �@@���v�w�l�p���s����(*6)=�f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)<BR>
     * �@@�@@�̖߂�l<BR>
     * �@@���v���~�A���^�����Y���i(*2) = �����P��.���t�w�l��l�^�C�v<BR>
     * �@@��������=�����P��.��������<BR>
     * <BR>
     * �@@�����P���敪 =<BR> 
     * �@@�@@[�����\�񒍕��P��Impl�̏ꍇ]<BR>
     * �@@�@@�@@�����\�񒍕��P��Impl.get���b�Z�[�W�p�����P���敪()�̖߂�l<BR>
     * �@@�@@[�敨OP�\�񒍕��P��Impl�̏ꍇ]<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����P���敪()�̖߂�l<BR>
     * �@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�����P��.isMarketOrder == true�̏ꍇ�A"���s"�B<BR>
     * �@@�@@�@@�ȊO�A"�w�l"���Z�b�g�B<BR>
     * �@@�����P�� = <BR>
     * �@@�@@[�����\�񒍕��P��Impl�̏ꍇ]<BR>
     * �@@�@@�@@�����\�񒍕��P��Impl.get���b�Z�[�W�p�����P��()�̖߂�l<BR>
     * �@@�@@[�敨OP�\�񒍕��P��Impl]<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����P��()�̖߂�l<BR>
     * �@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�����P��.isMarketOrder == false�̏ꍇ�A���̒l���Z�b�g�B<BR>
     * <BR>
     * �@@�������� = �����P��.�쐬���t<BR>
     * �@@������ = �����P��.������<BR>
     * �@@�@@�����L������ =<BR>
     * �@@�@@[���������P�� or �����\�񒍕��P��Impl�̏ꍇ]<BR>
     * �@@�@@�@@�����P��.���񒍕��̒����P��ID != null�̏ꍇ�̂݁A�����P��.�����������t�B<BR>
     * �@@�@@�@@�ȊO�Anull���Z�b�g�B<BR>
     * �@@�@@[�敨OP�����P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�敨OP�f�[�^�A�_�v�^.get���������敪�i�����P�ʁj<BR>
     * �@@�@@�@@��"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�����������t�B<BR>
     * �@@�@@�@@�ȊO�Anull���Z�b�g�B<BR>
     * �@@�@@[�敨OP�\�񒍕��P��Impl�̏ꍇ]<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����L������ ()�̖߂�l���Z�b�g�B<BR>
     * <BR>
     * �@@��n��� = �����P��.�T�Z��n���<BR>
     * �@@�������ϗ��R(*4) = �����P��.�������ϗ��R<BR>
     * �@@���������敪(*7) = �����P��.���������敪<BR>
     * <BR>
     * �@@�[��O�J�z�Ώۃt���O(*9) =<BR>
     * �@@�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(PR�w)(�����P��(**1))�̖߂�l���Z�b�g�B<BR>
     * �@@����敪(*10) = �����P��.����敪<BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@���s��R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A"0:DEFAULT"���Z�b�g�B<BR>
     * �@@�@@��L�ȊO�́A�擾�����s��R�[�h���Z�b�g�B<BR>
     * �@@�������R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A<BR>
     * �@@�@@�擾���������Y�����R�[�h<BR>
     * �@@����t���ԋ敪 =<BR>
     * �@@�@@this.get��t���ԋ敪(�����P��.�����J�e�S��, �����P��.����R�[�h�iSONAR�j)�̖߂�l���Z�b�g�B<BR>
     * <BR>
     * �T�j�@@�����󋵋敪���Z�b�g����B<BR>
     * �@@������(*6) = this.get������(�����P��)<BR>
     * <BR>
     * �U�j�@@�p�����[�^.is�\�t���O�ݒ� == true�̏ꍇ�A<BR>
     * �@@�@@�@@this.set�\�t���O()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set�\�t���O()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�A���������ׁF�@@�p�����[�^.�A����������<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �V�j�P�j�ɂđޔ������l������J�����_�R���e�L�X�g��<BR>
     * �@@�ăZ�b�g����B<BR>
     * <BR>
     * (*1)this.get����(�����P��.����ID, �����P��.�����^�C�v)<BR>
     * �@@�ɂ��擾�B<BR>
     * (*2)�敨OP�����P�ʂ̏ꍇ�Z�b�g�B�ȊO�Anull�B<BR>
     * (*3)�i�敨OP�����P�� or �敨OP�\�񒍕��P��Impl�j�̏ꍇ ���� <BR>
     * �@@�敨OP�����P��.�敨�^�I�v�V�����敪 = "�I�v�V����"�̏ꍇ�Z�b�g�B�ȊO�Anull�B<BR>
     * (*4)���������P�ʂ̏ꍇ�Z�b�g�B�ȊO�Anull�B<BR>
     * (*5)�i���������P�� or �����\�񒍕��P��Impl�j�̏ꍇ ����<BR>
     * �@@�����J�e�S�� != "��������"�̏ꍇ�Z�b�g�B�ȊO�Anull<BR>
     * (*6)���������P�� or �敨OP�����P�ʂ̏ꍇ�Z�b�g�B�ȊO�Anull<BR>
     * (*7)���������P�� or �����\�񒍕��P��Impl�̏ꍇ�Z�b�g�B�ȊO�Anull<BR>
     * (*8)�����P��.������ʁ�"����������"�̏ꍇ�́A�����P��.������ʂ����̂܂܃Z�b�g�B<BR>
     * �@@�@@�����P��.�������=="����������"�̏ꍇ�́A����R�[�h�iSONAR�j�̒l�ɂ�蕪��B<BR>
     * �@@�@@�|����R�[�h�iSONAR�j=="����O����"�̏ꍇ�F�@@"����O����"���Z�b�g�B<BR>
     * �@@�@@�|����R�[�h�iSONAR�j��"����O����"�̏ꍇ�F�@@"����������"���Z�b�g�B<BR>
     * (*9)�敨OP�����P�� or �敨OP�\�񒍕��P��Impl�̏ꍇ�Z�b�g�B�ȊO�Afalse�B<BR>
     * (*10)�敨OP�����P�� or �敨OP�\�񒍕��P��Impl�̏ꍇ�Z�b�g�B�ȊO�Anull�B<BR>
     * <BR>
     * (**1)�敨OP�\�񒍕��P��Impl�̏ꍇ�A<BR>
     * �A�������}�l�[�W��Impl.create�敨OP�����P��(�����P��)�̖߂�l���Z�b�g�B<BR>
     *
     * @@param l_succOrderUnit - (�A����������)<BR>
     * �A���������׃I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@param l_blnIsPossibleFlagSet - (is�\�t���O�ݒ�)<BR>
     * �����E����E�A�������\�t���O��<BR>
     * �ݒ肷�邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�ݒ肵�Ȃ�<BR>
     * true�F�@@�ݒ肷��<BR>
     * @@exception WEB3BaseException
     * @@roseuid 431E4E3A0093
     */
    public void createSuccOrderUnit(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createSuccOrderUnit(WEB3SuccOrderUnit, OrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_succOrderUnit == null || l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�@@����J�����_�R���e�L�X�g����ȉ��̒l�� 
        // �擾���A�ޔ�������B 
        // ���s��R�[�h 
        // �������R�[�h 
        // ����t���ԋ敪 
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //�s��R�[�h
        String l_strOldMarketCode = l_clendarContext.getMarketCode();
        //�����R�[�h 
        String l_strOldProductCode = l_clendarContext.getProductCode();
        //��t���ԋ敪 
        String l_strOldTradingTimeType = l_clendarContext.getTradingTimeType();
        
        if (!(l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl) &&
            !(l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl) &&
            !(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID = �����P��.����ID
        String l_strOrderId = new Long(l_orderUnit.getOrderId()).toString(); 

        //���i�敪 = this.get���i�敪(�����P��.�������) 
        String l_strCommodityType = 
            this.getCommodityDiv(l_orderUnit.getOrderType());

        //(*1)this.get����(�����P��.����ID, �����P��.�����^�C�v)�ɂ��擾�B
        ProductTypeEnum l_productTypeEnum = 
            l_orderUnit.getProduct().getProductType();

        //�����R�[�h = ����(*1).�����R�[�h
        String l_strProductCode = null;

        //������ = ����(*1).������
        String l_strProductName = null;

        //�w�����(*2) = ����.�����Y�����R�[�h
        String l_strTargetProductCode = null;
        //����(*2) = ����.���� 
        String l_strDelivaryMonth = null;
        //�I�v�V�������i�敪
        String l_strOpProductType = null;
        //�s�g���i
        String l_strStrikePrice = null;
        //�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h 
        String l_strMarketCode = null;
        //�����敪
        String l_strTaxType = null;
        //����敪 = �����P��.�������
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        //�ٍϋ敪
        String l_strRepaymentDiv = null;
        //�ٍϊ����l
        String l_strRepaymentTimeLimit = null;
        //�l�i����
        String l_strPriceCondType = null;
        //���s����
        String l_strExecCondType = null;
        //���������敪
        String l_strOrderConditionType = null;
        //�������� = �����P��.��������
        double l_dblOrderQuantity = l_orderUnit.getQuantity();
        //��������
        Date l_datOrderDate = null;
        //������
        String l_strOrderBizDate = null;
        //�����L������
        Date l_datExpirationDate = null;
        //��n���
        double l_dblDeliveryPrice = 0;
        //�������ϗ��R
        String l_strForcedSettleReason = null;
        //���������敪
        String l_strForcedExpireType = null;
        //������
        String l_strTransactionStateType = null;
        //����R�[�h�iSONAR�j
        String l_strSonarTradedCode = null;
        //�t�w�l�p�v���~�A��/�����Y���i(*2)
        String l_stopPremium_underlyingAssets = null;
        //�t�w�l�p���������P��(*6)
        String l_stopOrderCondPrice = null;
        //�t�w�l�p�����������Z�q(*6)
        String l_stopOrderCondOperator = null;
        //�v�w�l�p�v���~�A��/�����Y���i(*2)
        String l_wlimitPremium_underlyingAssets = null;
        //�v�w�l�p���������P��(*6)
        String l_wlimitOrderCondPrice = null;
        //�v�w�l�p�����������Z�q(*6)
        String l_wlimitOrderCondOperator = null;
        //�v�w�l�p�����P���敪(*6)
        String l_wLimitOrderPriceDiv = null;
        //�v�w�l�p�����P��(*6)
        String l_wLimitPrice = null;
        //�v�w�l�p���s����
        String l_strWLimitExecCondType = null;
        //�v�w�l�p�L����ԋ敪(*6)
        String l_strWLimitEnableStatusDiv = null;
        //  �@@�v�w�l�p�֑ؑO�����P��(*6) 
        String l_strWLimitBefSwitchPrice = null;
        //  �@@�v�w�l�p�֑ؑO���s����(*6)
        String l_strWLimitBefSwitchExecCondType = null;
        //  �@@�����������敪(*6)
        String l_strOrgOrderConditionType = null;
        //  �@@�����������P��(*6)
        String l_strOrgOrderPrice = null;
        //  �@@�������������Z�q(*6)
        String l_strOrgOrderCondOperator = null;
        //  �@@���v�w�l�p�����P���敪(*6)
        String l_strOrgWLimitOrderPriceDiv = null;
        //  �@@���v�w�l�p�����P��(*6)
        String l_strOrgWLimitOrderPrice = null;
        //  �@@���v�w�l�p���s����(*6)
        String l_strOrgWLimitExecCondType = null;
        //  �@@�����P���敪
        String l_strOrderPriceDiv = null;
        //  �@@�����P��
        String l_strPrice = null;
        //���v���~�A���^�����Y���i
        String l_strOrgStopPriceType = null;
        //�[��O�J�z�Ώۃt���O
        boolean l_blnIsEveningSessionCarryOverFlag = false;
        //����敪
        String l_strSessionType = null;

        try
        {
            //�����\�񒍕��P��Impl�̏ꍇ
            if (l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
                    (RsvEqOrderUnitRow)((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit).getDataSourceObject();

                //(*1)this.get����(�����P��.����ID, �����P��.�����^�C�v)�ɂ��擾�B
                EqtypeProductRow l_eqtypeProductRow = 
                    (EqtypeProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //�����R�[�h
                l_strProductCode = l_eqtypeProductRow.getProductCode();

                //������
                l_strProductName = l_eqtypeProductRow.getStandardName();

                //�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_rsvEqOrderUnitRow.getMarketId()).getMarketCode();

                //�����敪(*7) = �����f�[�^�A�_�v�^.get�����敪(���� �����P��.getTaxType) 
                l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

                //���������P��.�����J�e�S�� != "��������"�̏ꍇ
                if (!OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
                {
                    //�ٍϋ敪(*5) = �����P��.�ٍϋ敪
                    l_strRepaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
                    //�ٍϊ����l(*5) = �����P��.�ٍϊ����l
                    l_strRepaymentTimeLimit = 
                        new Long(l_rsvEqOrderUnitRow.getRepaymentNum()).toString();
                }

                WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnitImpl = 
                    (WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit;
                //�����\�񒍕��P��Impl.get���b�Z�[�W�p�����P���敪()�̖߂�l 
                l_strOrderPriceDiv = l_rsvEqOrderUnitImpl.getMsgOrderPriceDiv();

                //�����\�񒍕��P��Impl.get���b�Z�[�W�p�����P��()�̖߂�l
                l_strPrice = l_rsvEqOrderUnitImpl.getMsgLimitPrice();

                //�������� = �����P��.�쐬���t
                l_datOrderDate = l_rsvEqOrderUnitRow.getCreatedTimestamp();

                //������ = �����P��.������
                l_strOrderBizDate = l_rsvEqOrderUnitRow.getBizDate();

                //�����L������ = �����P��.���񒍕��̒����P��ID != null�̏ꍇ�̂݁A 
                //�����P��.�����������t�B�ȊO�Anull���Z�b�g�B 
                if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_datExpirationDate = l_rsvEqOrderUnitRow.getExpirationDate();
                }

                //��n��� = �����P��.�T�Z��n���
                l_dblDeliveryPrice = l_rsvEqOrderUnitRow.getEstimatedPrice();

                //���������敪(*7) = �����P��.���������敪
                l_strForcedExpireType = l_rsvEqOrderUnitRow.getForcedExpireType();
            }
            //�敨OP�\�񒍕��P��Impl�̏ꍇ
            else if (l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = 
                    (RsvIfoOrderUnitRow)((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit).getDataSourceObject();

                //this.get����(�����P��.����ID, �����P��.�����^�C�v)�ɂ��擾�B
                IfoProductRow l_ifoProductRow = 
                    (IfoProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //�����R�[�h
                l_strProductCode = l_ifoProductRow.getProductCode();

                //������
                l_strProductName = l_ifoProductRow.getStandardName();
                
                //�敨OP�����P��.�敨�^�I�v�V�����敪 = "�I�v�V����"�̏ꍇ
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
                {
                    if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                    }
                    else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                    }

                    //�s�g���i(*3) = ����.�s�g���i
                    l_strStrikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());
                }

                //�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = new WEB3GentradeMarket(
                    l_rsvIfoOrderUnitRow.getMarketId()).getMarketCode();

                WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnitImpl = 
                    (WEB3ToSuccIfoOrderUnitImpl)l_orderUnit;
                
                //�w�����(*10) = ����.�����Y�����R�[�h
                l_strTargetProductCode = l_ifoProductRow.getUnderlyingProductCode();

                //����(*10) = ����.����
                l_strDelivaryMonth = l_ifoProductRow.getMonthOfDelivery();
                
                //�����P���敪
                //�敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����P���敪()�̖߂�l 
                l_strOrderPriceDiv = l_rsvIfoOrderUnitImpl.getMsgOrderPriceDiv();

                //�����P��
                //�敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����P��()�̖߂�l
                l_strPrice = l_rsvIfoOrderUnitImpl.getMsgLimitPrice();

                //�������� = �����P��.�쐬���t
                l_datOrderDate = l_rsvIfoOrderUnitRow.getCreatedTimestamp();

                //������ = �����P��.������
                l_strOrderBizDate = l_rsvIfoOrderUnitRow.getBizDate();

                //�����L������ = �敨OP�\�񒍕��P��Impl.get���b�Z�[�W�p�����L������ ()�̖߂�l���Z�b�g�B
                l_datExpirationDate = l_rsvIfoOrderUnitImpl.getMsgExpirationDate();

                //��n��� = �����P��.�T�Z��n���
                l_dblDeliveryPrice = l_rsvIfoOrderUnitRow.getEstimatedPrice();

                WEB3ToSuccOrderManagerImpl l_toSuceeOrderManager =
                    (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
                
                //�[��O�J�z�Ώۃt���O(*9) = �f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(PR�w)(�����P��(**1)))�̖߂�l���Z�b�g�B
                l_blnIsEveningSessionCarryOverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(
                        l_toSuceeOrderManager.createIfoOrderUnit(l_rsvIfoOrderUnitImpl));
                
                //����敪(*9) = �����P��.����敪
                l_strSessionType = l_rsvIfoOrderUnitRow.getSessionType();
            }
            //(*2)�敨OP�����P�ʂ̏ꍇ�Z�b�g�B�ȊO�Anull
            else if (l_orderUnit instanceof IfoOrderUnit)
            {
                //(*1)this.get����(�����P��.����ID, �����P��.�����^�C�v)�ɂ��擾�B
                IfoProductRow l_ifoProductRow = 
                    (IfoProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //�����R�[�h
                l_strProductCode = l_ifoProductRow.getProductCode();

                //������
                l_strProductName = l_ifoProductRow.getStandardName();

                //�w�����(*10) = ����.�����Y�����R�[�h
                l_strTargetProductCode = l_ifoProductRow.getUnderlyingProductCode();

                //����(*10) = ����.����
                l_strDelivaryMonth = l_ifoProductRow.getMonthOfDelivery();
                IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow)((IfoOrderUnit)l_orderUnit).getDataSourceObject();

                //�敨OP�����P��.�敨�^�I�v�V�����敪 = "�I�v�V����"�̏ꍇ
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                    }
                    else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                    }

                    //�s�g���i(*3) = ����.�s�g���i
                    l_strStrikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());
                }

                //�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = new WEB3GentradeMarket(
                    l_ifoOrderUnitRow.getMarketId()).getMarketCode();

                //���s����(*6) = this.get���s�����iPR�w�j(�����P��)
                l_strExecCondType =
                    this.getExecutionConditionTypeByPr(l_orderUnit);

                //���������敪(*6) = �����P��.�������� 
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

                //�������� = �����P��.�쐬���t
                l_datOrderDate = l_ifoOrderUnitRow.getCreatedTimestamp();

                //������ = �����P��.������
                l_strOrderBizDate = l_ifoOrderUnitRow.getBizDate();

                //�����L������ =
                //�敨OP�f�[�^�A�_�v�^.get���������敪�i�����P�ʁj��"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�����������t�B
                //�ȊO�Anull���Z�b�g�B
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                    WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
                {
                    l_datExpirationDate = l_ifoOrderUnitRow.getExpirationDate();
                }

                //��n��� = �����P��.�T�Z��n��� 
                l_dblDeliveryPrice = l_ifoOrderUnitRow.getEstimatedPrice();
                
                //�����P��.�����J�e�S��
                  OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
                
                //����R�[�h�iSONAR�j
                l_strSonarTradedCode = l_ifoOrderUnitRow.getSonarTradedCode();
                
                //�@@����J�����_�R���e�L�X�g�Ɉȉ��̒l���Z�b�g����B 
                //  ���s��R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A"0:DEFAULT"���Z�b�g�B  
                //  �������R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A
                //    �擾���������Y�����R�[�h 
                if (l_strTargetProductCode != null)
                {
                    l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                    l_clendarContext.setProductCode(l_strTargetProductCode); 
                }
                else
                {
                    //��L�ȊO�́A�擾�����s��R�[�h���Z�b�g�B
                    l_clendarContext.setMarketCode(l_strMarketCode);
                }

                //  ����t���ԋ敪 = this.get��t���ԋ敪()�̖߂�l���Z�b�g�B
                l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_orderCateg, l_strSonarTradedCode));

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
                
                //������(*6) = this.get������(�����P��) 
                l_strTransactionStateType = this.getTransactionState(l_orderUnit);
                
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
                //[�����P��.�������� == "�t�w�l"�̏ꍇ]
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //�t�w�l�p�v���~�A���^�����Y���i(*2) = �����P��.�t�w�l��l�^�C�v
                    l_stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                        
                    //�t�w�l�p���������P��(*6) = �����P��.�t�w�l��l
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }

                    //�t�w�l�p�����������Z�q(*6) = �����P��.�����������Z�q
                    l_stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                }
                //[�����P��.�������� == "W�w�l"�̏ꍇ]
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //�v�w�l�p�v���~�A���^�����Y���i(*2) = �����P��.�t�w�l��l�^�C�v
                    l_wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                    //�v�w�l�p���������P��(*6) = �����P��.�t�w�l��l
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }
                    //�v�w�l�p�����������Z�q(*6) = �����P��.�����������Z�q
                    l_wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                    if (Double.compare(l_ifoOrderUnitRow.getWLimitPrice(), 0) == 0)
                    {
                        //�v�w�l�p�����P���敪(*6) = �����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�A"���s"�B
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        //�v�w�l�p�����P���敪(*6) = �����P��.�iW�w�l�j�����w�l != 0�̏ꍇ�A"�w�l"�B
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                        //�v�w�l�p�����P��(*6) = �����P��.�iW�w�l�j�����w�l != 0�̏ꍇ�A���̒l���Z�b�g�B
                        l_wLimitPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                    }
                }

                //    ���ȉ��A�u�f�[�^�A�_�v�^�v�͒����P�ʂ̌^�ɂ�莟�̂悤�ɓǂݑւ��鎖�B
                //  �@@�@@�敨OP�����P�ʂ̏ꍇ�A�u�敨OP�f�[�^�A�_�v�^
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;

                //�@@�v�w�l�p���s����(*6) = �f�[�^�A�_�v�^.get�v�w�l�p���s����(�����P��)�̖߂�l
                l_strWLimitExecCondType = WEB3IfoDataAdapter.getWLimitExecCondType(l_ifoOrderUnit);

                //  �@@�v�w�l�p�L����ԋ敪(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)�̖߂�l
                l_strWLimitEnableStatusDiv =
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);

                //  �@@�v�w�l�p�֑ؑO�����P��(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l
                l_strWLimitBefSwitchPrice =
                    WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);

                //  �@@�v�w�l�p�֑ؑO���s����(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)�̖߂�l
                l_strWLimitBefSwitchExecCondType =
                    WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);

                //  �@@�����������敪(*6) = �����P��.����������
                l_strOrgOrderConditionType =
                    l_ifoOrderUnitRow.getOrgOrderConditionType();

                //  �@@�����������P��(*6) = �����P��.���t�w�l��l
                l_strOrgOrderPrice = null;
                if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_strOrgOrderPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getOrgStopOrderPrice());
                }

                //  �@@�������������Z�q(*6) = �����P��.�������������Z�q
                l_strOrgOrderCondOperator =
                    l_ifoOrderUnitRow.getOrgOrderCondOperator();

                //  �@@���v�w�l�p�����P���敪(*6) = �f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)�̖߂�l
                l_strOrgWLimitOrderPriceDiv =
                    WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);

                //  �@@���v�w�l�p�����P��(*6) = ��W�w�l�p�����P���敪 == "�w�l"�̏ꍇ�̂�
                //  �@@�@@�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)�̖߂�l
                l_strOrgWLimitOrderPrice = null;
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_strOrgWLimitOrderPrice =
                        WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
                }

                //  �@@���v�w�l�p���s����(*6) = �f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)�̖߂�l
                l_strOrgWLimitExecCondType =
                    WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);

                //   �����P���敪 = �����P��.isMarketOrder == true�̏ꍇ�A"���s"�B 
                if (l_orderUnit.isMarketOrder())
                {
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {   //  �@@�@@�@@�ȊO�A"�w�l"���Z�b�g
                    //  �@@�����P��.isMarketOrder == false�̏ꍇ�A���̒l���Z�b�g�B
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_strPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getLimitPrice());
                }

                //���v���~�A���^�����Y���i(*2) = �����P��.���t�w�l��l�^�C�v
                l_strOrgStopPriceType = l_ifoOrderUnitRow.getOrgStopPriceType();

                //�[��O�J�z�Ώۃt���O(*9) = �f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(PR�w)(�����P��)�̖߂�l���Z�b�g�B
                l_blnIsEveningSessionCarryOverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_ifoOrderUnit);

                //����敪(*9) = �����P��.����敪
                l_strSessionType = l_ifoOrderUnitRow.getSessionType();
            }

            //(*4)���������P�ʂ̏ꍇ
            else if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                    (EqtypeOrderUnitRow)(((EqTypeOrderUnit)l_orderUnit).getDataSourceObject());
                

                //(*1)this.get����(�����P��.����ID, �����P��.�����^�C�v)�ɂ��擾�B
                EqtypeProductRow l_eqtypeProductRow = 
                    (EqtypeProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //�����R�[�h
                l_strProductCode = l_eqtypeProductRow.getProductCode();

                //������
                l_strProductName = l_eqtypeProductRow.getStandardName();

                //�s��R�[�h = �����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();

                //�����P��.�����J�e�S��
                OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            
                //�����敪(*4) = �����f�[�^�A�_�v�^.get�����敪(���� �����P��.getTaxType)
                l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
            
                //���������P��.�����J�e�S�� != "��������"�̏ꍇ
                if (!OrderCategEnum.ASSET.equals(l_orderCateg))
                {
                    //�ٍϋ敪(*5) = �����P��.�ٍϋ敪
                    l_strRepaymentDiv = l_eqTypeOrderUnitRow.getRepaymentType();
                    //�ٍϊ����l(*5) = �����P��.�ٍϊ����l
                    l_strRepaymentTimeLimit = 
                        new Long(l_eqTypeOrderUnitRow.getRepaymentNum()).toString();
                }
            
                //�l�i����(*4) = �����P��.�l�i����
                l_strPriceCondType = l_eqTypeOrderUnitRow.getPriceConditionType();

                //���s����(*6) = this.get���s�����iPR�w�j(�����P��)
                l_strExecCondType = 
                    this.getExecutionConditionTypeByPr(l_orderUnit);

                //���������敪(*6) = �����P��.��������
                l_strOrderConditionType = l_eqTypeOrderUnitRow.getOrderConditionType();

                //�������� = �����P��.�쐬���t
                l_datOrderDate = l_eqTypeOrderUnitRow.getCreatedTimestamp();

                //������ = �����P��.������
                l_strOrderBizDate = l_eqTypeOrderUnitRow.getBizDate();

                //�����L������ = �����P��.���񒍕��̒����P��ID != null�̏ꍇ�̂݁A 
                //�����P��.�����������t�B�ȊO�Anull���Z�b�g�B 
                if (!l_eqTypeOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_datExpirationDate = l_eqTypeOrderUnitRow.getExpirationDate();
                }

                //��n��� = �����P��.�T�Z��n��� 
                l_dblDeliveryPrice = l_eqTypeOrderUnitRow.getEstimatedPrice();

                //�������ϗ��R(*4) = �����P��.�������ϗ��R
                l_strForcedSettleReason = l_eqTypeOrderUnitRow.getForcedSettleReasonType();

                //���������敪(*7) = �����P��.���������敪
                l_strForcedExpireType = l_eqTypeOrderUnitRow.getForcedExpireType();

                //����R�[�h�iSONAR�j
                l_strSonarTradedCode = l_eqTypeOrderUnitRow.getSonarTradedCode();

                //�@@����J�����_�R���e�L�X�g�Ɉȉ��̒l���Z�b�g����B 
                //  ���s��R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A"0:DEFAULT"���Z�b�g�B  
                //  �������R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A
                //    �擾���������Y�����R�[�h 
                if (l_strTargetProductCode != null)
                {
                    l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                    l_clendarContext.setProductCode(l_strTargetProductCode); 
                }
                else
                {
                    //��L�ȊO�́A�擾�����s��R�[�h���Z�b�g�B
                    l_clendarContext.setMarketCode(l_strMarketCode);
                }

                //  ����t���ԋ敪 = this.get��t���ԋ敪()�̖߂�l���Z�b�g�B
                l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_orderCateg, l_strSonarTradedCode));

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
                
                //������(*6) = this.get������(�����P��) 
                l_strTransactionStateType = this.getTransactionState(l_orderUnit);
                
                //[�����P��.�������� == "�t�w�l"�̏ꍇ]
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //�t�w�l�p���������P��(*6) = �����P��.�t�w�l��l
                    if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_eqTypeOrderUnitRow.getStopOrderPrice());
                    }

                    //�t�w�l�p�����������Z�q(*6) = �����P��.�����������Z�q
                    l_stopOrderCondOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();
                }
                //[�����P��.�������� == "W�w�l"�̏ꍇ]                
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //�v�w�l�p���������P��(*6) = �����P��.�t�w�l��l
                    if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(
                                l_eqTypeOrderUnitRow.getStopOrderPrice());
                    }

                    //�v�w�l�p�����������Z�q(*6) = �����P��.�����������Z�q
                    l_wlimitOrderCondOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();

                    if (Double.compare(l_eqTypeOrderUnitRow.getWLimitPrice(), 0) == 0)
                    {
                        //�v�w�l�p�����P���敪(*6) = �����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�A"���s"�B
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        //�v�w�l�p�����P���敪(*6) = �����P��.�iW�w�l�j�����w�l != 0�̏ꍇ�A"�w�l"�B
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                        //�v�w�l�p�����P��(*6) = �����P��.�iW�w�l�j�����w�l != 0�̏ꍇ�A���̒l���Z�b�g�B
                        l_wLimitPrice =
                            WEB3StringTypeUtility.formatNumber(l_eqTypeOrderUnitRow.getWLimitPrice());
                    }
                }
                //���ȉ��A�u�f�[�^�A�_�v�^�v�͒����P�ʂ̌^�ɂ�莟�̂悤�ɓǂݑւ��鎖�B
                //  �@@�@@���������P�ʂ̏ꍇ�A�u�����f�[�^�A�_�v�^
                EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

                //�@@�v�w�l�p���s����(*6) = �f�[�^�A�_�v�^.get�v�w�l�p���s����(�����P��)�̖߂�l
                l_strWLimitExecCondType = WEB3EquityDataAdapter.getWLimitExecCondType(l_eqTypeOrderUnit);
                
                //  �@@�v�w�l�p�L����ԋ敪(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)�̖߂�l
                l_strWLimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_eqTypeOrderUnit);

                //  �@@�v�w�l�p�֑ؑO�����P��(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l
                l_strWLimitBefSwitchPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_eqTypeOrderUnit);

                //  �@@�v�w�l�p�֑ؑO���s����(*6) = �f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)�̖߂�l
                l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_eqTypeOrderUnit);

                //  �@@�����������敪(*6) = �����P��.����������
                l_strOrgOrderConditionType =
                    l_eqTypeOrderUnitRow.getOrgOrderConditionType();

                //  �@@�����������P��(*6) = �����P��.���t�w�l��l
                l_strOrgOrderPrice = null;
                if (!l_eqTypeOrderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_strOrgOrderPrice = WEB3StringTypeUtility.formatNumber(
                        l_eqTypeOrderUnitRow.getOrgStopOrderPrice());
                }

                //  �@@�������������Z�q(*6) = �����P��.�������������Z�q
                l_strOrgOrderCondOperator =
                    l_eqTypeOrderUnitRow.getOrgOrderCondOperator();

                //  �@@���v�w�l�p�����P���敪(*6) = �f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)�̖߂�l
                l_strOrgWLimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_eqTypeOrderUnit);

                //  �@@���v�w�l�p�����P��(*6) = ��W�w�l�p�����P���敪 == "�w�l"�̏ꍇ�̂�
                //  �@@�@@�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)�̖߂�l
                l_strOrgWLimitOrderPrice = null;
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_strOrgWLimitOrderPrice =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_eqTypeOrderUnit);
                }

                //  �@@���v�w�l�p���s����(*6) = �f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)�̖߂�l
                l_strOrgWLimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_eqTypeOrderUnit);

                //   �����P���敪 = �����P��.isMarketOrder == true�̏ꍇ�A"���s"�B 
                if (l_orderUnit.isMarketOrder())
                {
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {   //  �@@�@@�@@�ȊO�A"�w�l"���Z�b�g
                    //  �@@�����P��.isMarketOrder == false�̏ꍇ�A���̒l���Z�b�g�B
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_strPrice = WEB3StringTypeUtility.formatNumber(
                        l_eqTypeOrderUnitRow.getLimitPrice());
                }

            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //����ID
        l_succOrderUnit.orderId = l_strOrderId; 
        //���i�敪
        l_succOrderUnit.commodityType = l_strCommodityType;
        //�����R�[�h
        l_succOrderUnit.productCode = l_strProductCode;
        //������
        l_succOrderUnit.productName = l_strProductName;
        //�w�����
        l_succOrderUnit.targetProductCode = l_strTargetProductCode;
        //����
        l_succOrderUnit.delivaryMonth = l_strDelivaryMonth;
        //�I�v�V�������i�敪
        l_succOrderUnit.opProductType = l_strOpProductType;
        //�s�g���i
        l_succOrderUnit.strikePrice = l_strStrikePrice;
        //�s��R�[�h
        l_succOrderUnit.marketCode = l_strMarketCode;
        //�����敪
        l_succOrderUnit.taxType = l_strTaxType;
        //����敪�@@������O�����̏ꍇ�̂݁A�R�[�h�ϊ����K�v
        if (!OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_succOrderUnit.tradingType = new Long(l_orderType.intValue()).toString();
        }
        else
        {
            if ((l_strSonarTradedCode != null) &&
                (l_strSonarTradedCode.equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET)))
            {
                l_succOrderUnit.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
            else
            {
                l_succOrderUnit.tradingType = new Long(l_orderType.intValue()).toString();
            }
        }
        //�ٍϋ敪
        l_succOrderUnit.repaymentDiv = l_strRepaymentDiv;
        //�ٍϊ����l
        l_succOrderUnit.repaymentTimeLimit = l_strRepaymentTimeLimit;
        //�l�i����
        l_succOrderUnit.priceCondType = l_strPriceCondType;
        //���s����
        l_succOrderUnit.execCondType = l_strExecCondType;
        //���������敪
        l_succOrderUnit.orderCondType = l_strOrderConditionType;
        //��������
        l_succOrderUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity); 
        //��������
        l_succOrderUnit.orderDate = l_datOrderDate;
        //������
        l_succOrderUnit.orderBizDate = 
            WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd");
        //�����L������
        l_succOrderUnit.expirationDate = l_datExpirationDate;
        //��n���
        l_succOrderUnit.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);

        //�������ϗ��R(*4) = �����P��.�������ϗ��R
        l_succOrderUnit.forcedSettleReason = l_strForcedSettleReason;

        //���������敪(*7) = �����P��.���������敪
        l_succOrderUnit.forcedLapseDiv = l_strForcedExpireType;

        //������
        l_succOrderUnit.transactionStateType = l_strTransactionStateType;
        //�t�w�l�p�v���~�A��/�����Y���i
        l_succOrderUnit.stopPremium_underlyingAssets = l_stopPremium_underlyingAssets;
        //�t�w�l�p���������P��
        l_succOrderUnit.stopOrderCondPrice = l_stopOrderCondPrice;
        //�t�w�l�p�����������Z�q
        l_succOrderUnit.stopOrderCondOperator = l_stopOrderCondOperator;
        //�v�w�l�p�v���~�A��/�����Y���i
        l_succOrderUnit.wlimitPremium_underlyingAssets = l_wlimitPremium_underlyingAssets;
        //�v�w�l�p���������P��
        l_succOrderUnit.wlimitOrderCondPrice = l_wlimitOrderCondPrice;
        //�v�w�l�p�����������Z�q
        l_succOrderUnit.wlimitOrderCondOperator = l_wlimitOrderCondOperator;
        //�v�w�l�p�����P���敪
        l_succOrderUnit.wLimitOrderPriceDiv = l_wLimitOrderPriceDiv;
        //�v�w�l�p�����P��
        l_succOrderUnit.wLimitPrice = l_wLimitPrice;

        //�v�w�l�p���s����
        l_succOrderUnit.wlimitExecCondType = l_strWLimitExecCondType;

        //�v�w�l�p�L����ԋ敪(*6)
        l_succOrderUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

        //  �@@�v�w�l�p�֑ؑO�����P��(*6)
        l_succOrderUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

        //  �@@�v�w�l�p�֑ؑO���s����(*6)
        l_succOrderUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //  �@@�����������敪(*6)
        l_succOrderUnit.orgOrderCondType = l_strOrgOrderConditionType;

        //  �@@�����������P��(*6)
        l_succOrderUnit.orgOrderCondPrice = l_strOrgOrderPrice;

        //  �@@�������������Z�q(*6)
        l_succOrderUnit.orgOrderCondOperator = l_strOrgOrderCondOperator;

        //  �@@���v�w�l�p�����P���敪(*6)
        l_succOrderUnit.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //  �@@���v�w�l�p�����P��(*6)
        l_succOrderUnit.orgWlimitPrice = l_strOrgWLimitOrderPrice;

        //  �@@���v�w�l�p���s����(*6)
        l_succOrderUnit.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

        //  �@@�����P���敪
        l_succOrderUnit.orderPriceDiv = l_strOrderPriceDiv;

        //  �@@�����P��
        l_succOrderUnit.orderPrice = l_strPrice;

        //���v���~�A���^�����Y���i
        l_succOrderUnit.orgPremium_underlyingAssets = l_strOrgStopPriceType;

        //�[��O�J�z�Ώۃt���O
        l_succOrderUnit.eveningSessionCarryoverFlag = l_blnIsEveningSessionCarryOverFlag;

        //����敪
        l_succOrderUnit.sessionType = l_strSessionType;

        //�p�����[�^.is�\�t���O�ݒ� == true�̏ꍇ
        if (l_blnIsPossibleFlagSet)
        {
            //this.set�\�t���O()���R�[������
            this.setPossibleFlag(l_succOrderUnit, l_orderUnit);
        }
        
        // �ޔ������l������J�����_�R���e�L�X�g�� 
        // �ăZ�b�g����B
        l_clendarContext.setMarketCode(l_strOldMarketCode);
        l_clendarContext.setProductCode(l_strOldProductCode);
        l_clendarContext.setTradingTimeType(l_strOldTradingTimeType);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�\�񒍕�����)<BR>
     * �����̑O�񒍕����ׂɗ\�񒍕����ׂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g����ȉ��̒l���擾���A�ޔ�������B<BR>
     * �@@�@@���s��R�[�h<BR>
     * �@@�@@�������R�[�h<BR>
     * �@@�@@����t���ԋ敪<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�񒍕�Row�ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�������Ώۂ̗v�f�������\�񒍕��P��Row�^�A<BR>
     * �@@�@@�@@�܂��͐敨OP�\�񒍕��P��Row�^�ɃL���X�g����B<BR>
     * �@@�R�|�P�j�@@�\�񒍕��P�ʃC���X�^���X�𐶐�����B<BR>
     * �@@�@@�A�������}�l�[�W��Impl.toOrderUnit()���R�[������B<BR>
     * <BR>
     * �@@�@@[toOrderUnit()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P��Row�F�@@�����Ώۂ̗v�f<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�\�񒍕�������(�\�񒍕��P��.is������() == true)�̏ꍇ�A<BR>
     * �@@�@@�e���i�̒����P�ʂ��擾����B<BR>
     * �@@�@@this.get�����P��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get�����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P��ID�F�@@�����Ώۂ̗v�f.�����P��ID<BR>
     * �@@�@@�@@�����^�C�v�F�@@�����Ώۂ̗v�f.�����^�C�v<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����Ώۂ̒����P�ʁi=OrderUnit�j�����肷��B<BR>
     * �@@�@@[�\�񒍕�������(�\�񒍕��P��.is������() == true)�̏ꍇ]<BR>
     * �@@�@@�@@�����Ώۂ̒����P�� = �R�|�Q�j�̖߂�l<BR>
     * <BR>
     * �@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�����Ώۂ̒����P�� = �R�|�P�j�̖߂�l<BR>
     * <BR>
     * �@@�R�|�S�j�@@����J�����_�R���e�L�X�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@���s��R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A"0:DEFAULT"���Z�b�g�B<BR>
     * �@@�@@��L�ȊO�́A�擾�����s��R�[�h���Z�b�g�B<BR>
     * �@@�������R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A<BR>
     * �@@�@@�擾���������Y�����R�[�h<BR>
     * �@@����t���ԋ敪 = this.get��t���ԋ敪(�\�񒍕��P��.�����J�e�S��, null)�̖߂�l���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�T�j�@@�\�񒍕����׃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�U�j�@@this.create�A����������()���R�[������B<BR>
     * <BR>
     * �@@�@@[create�A����������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�A���������ׁF�@@���������\�񒍕�����<BR>
     * �@@�@@�@@�����P�ʁF�@@�����Ώۂ̒����P��<BR>
     * �@@�@@�@@is�\�t���O�ݒ�F�@@false(�ݒ肵�Ȃ�)<BR>
     * <BR>
     * �@@�R�|�V�j�@@���������\�񒍕����ׂɈȉ��̃v���p�e�B��<BR>
     * �@@�@@�Z�b�g����B<BR>
     * �@@�@@���\�񒍕��P�ʂ���擾����e���ڂ́A<BR>
     * �@@�@@�@@���ڐݒ肹���A��x�ʕϐ��Ɋi�[����悤�Ɏ������邱�ƁB<BR>
     * <BR>
     * �@@�@@�l�i���� = "�w��Ȃ�"(*3)<BR>
     * �@@�@@���s���� = "������"(*4)<BR>
     * �@@�@@���������敪 = "�w��Ȃ�"(*4)<BR>
     * �@@�@@�P�������l = �\�񒍕��P��(*1).is�}�w�l�w��()==true�̏ꍇ�A<BR>
     * �@@�@@�@@���̒l���Z�b�g�B(*5)<BR>
     * �@@�@@�e�����̒���ID = �\�񒍕��P��.�e�����̒���ID<BR>
     * �@@�@@�e�������A�� = �\�񒍕��P��.�e�������A��<BR>
     * �@@�@@�����G���[���R�R�[�h =<BR>
     * �@@�@@�@@[�����\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.get�����G���[���R�R�[�h(�\�񒍕��P��.�����G���[�R�[�h)<BR>
     * �@@�@@�@@[�敨OP�\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@OP�����}�l�[�W��.get�����G���[���R�R�[�h(�\�񒍕��P��.�����G���[�R�[�h)<BR>
     * <BR>
     * �@@�@@�@@���������������s����Ă��Ȃ��i�\�񒍕��P��.is�������s��()==false�j�ꍇ�́Anull���Z�b�g�B<BR>
     * �@@�@@������ = (*2)<BR>
     * <BR>
     * �@@�@@(*1)�R�|�P�j�̖߂�l��instanceof�ɂĔ��ʂ��A<BR>
     * �@@�@@�@@�@@�ȉ��̂����ꂩ�ɃL���X�g���Ď擾�B<BR>
     * �@@�@@�@@�@@�@@�E�����\�񒍕��P��Impl<BR>
     * �@@�@@�@@�@@�@@�E�敨OP�\�񒍕��P��Impl<BR>
     * <BR>
     * �@@�@@(*2)this.get������()�̖߂�l���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����P�ʁF�@@�����Ώۂ̒����P��<BR>
     * <BR>
     * �@@�@@(*3)�����ς̏ꍇ(�\�񒍕��P��.is������()==true)�́A<BR>
     * �@@�@@�@@�@@�����Ώۂ̗v�f.�����^�C�v=="����"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�R�|�Q�j�Ŏ擾�����e���i�̒����P�ʂ̓����ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@(*4)�����ς̏ꍇ(�\�񒍕��P��.is������()==true)�́A <BR>
     * �@@�@@�@@�@@�R�|�Q�j�Ŏ擾�����e���i�̒����P�ʂ̓����ڂ��Z�b�g����B <BR>
     * �@@�@@�@@�@@�������A���s������this.get���s�����iPR�w�j() <BR>
     * �@@�@@�@@�@@��SONAR�̃R�[�h�̌n�ɕϊ����ăZ�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get���s�����iPR�w�j()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʁF�@@�R�|�Q�j�Ŏ擾���������P�� <BR>
     * <BR>
     * �@@�@@(*5)�����ς̏ꍇ(�\�񒍕��P��.is������()==true)�́A<BR>
     * �@@�@@�@@�@@null���Z�b�g����B<BR>
     * <BR>
     * �@@�R�|�W�j�@@�p�����[�^.is�\�t���O�ݒ� == true�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ɂ��e�\�t���O��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@�@@[�������̏ꍇ(�\�񒍕��P��.is������()==false)]<BR>
     * �@@�@@�@@�@@�|�\�񒍕��P��.�����L����� == "�N���[�Y"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃v���p�e�B��false���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�E�\�񒍕�����.�����\�t���O<BR>
     * �@@�@@�@@�@@�@@�@@�E�\�񒍕�����.����\�t���O<BR>
     * <BR>
     * �@@�@@�@@�@@�|������ԊǗ�.validate�A��������t�\()(*6)����O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃v���p�e�B��false���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�E�\�񒍕�����.�����\�t���O<BR>
     * �@@�@@�@@�@@�@@�@@�E�\�񒍕�����.����\�t���O<BR>
     * <BR>
     * �@@�@@�@@�@@�|�������n�����i�\�񒍕��P��.�����J�e�S��=="�������n"�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃v���p�e�B��false���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�E�\�񒍕�����.�����\�t���O<BR>
     * <BR>
     * �@@�@@�@@�@@[������(��L�ȊO)�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@this.set�\�t���O()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[set�\�t���O()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�A���������ׁF�@@�v���p�e�B�Z�b�g�����\�񒍕�����<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʁF�@@�����Ώۂ̒����P��<BR>
     * <BR>
     * �@@�@@�@@�@@(*6)�@@[����]<BR>
     * �@@�@@�@@�@@�@@�،���ЁF�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h�ɊY������،����<BR>
     * �@@�@@�@@�@@�@@�����^�C�v�F�@@�\�񒍕��P��.�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�敨�^�I�v�V�����敪�F<BR>
     * �@@�@@�@@�@@�@@�@@[�����\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@"DEFAULT" <BR>
     * �@@�@@�@@�@@�@@�@@[�敨OP�\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�\�񒍕��P��.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�@@�@@�o���I���敪�F<BR>
     * �@@�@@�@@�@@�@@�@@[�����\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@"�o���I���i�ŏI�j"<BR>
     * �@@�@@�@@�@@�@@�@@[�敨OP�\�񒍕��P��Row�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch(<BR>
     * �@@�@@�@@�@@�@@�@@�擾�������XID).is�[����{(�\�񒍕��P��.�����^�C�v) == true and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�\�񒍕��P��.����敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"�o���I���i�ŏI�j"<BR>
     * <BR>
     * �@@�R�|�X�j�@@�v���p�e�B�Z�b�g�����\�񒍕�����.�A�������\�t���O = false��<BR>
     * �@@�@@�@@�@@�@@�@@�Z�b�g����B<BR>
     * �@@�@@���q�����͘A���ݒ�s�B<BR>
     * <BR>
     * �@@�R�|�P�O�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����\�񒍕����ׂ�ǉ�����B<BR>
     * <BR>
     * �S�j�@@�p�����[�^.�O�񒍕�����.�\�񒍕��ꗗ��<BR>
     * �@@ArrayList.toArray()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �T�j�@@�P�j�ɂđޔ������l������J�����_�R���e�L�X�g�ɍăZ�b�g����B<BR>
     * @@param l_requiredOrderUnit - (�O�񒍕�����)<BR>
     * �O�񒍕�����
     * @@param l_rsvOrderRowList - (�\�񒍕�Row�ꗗ)<BR>
     * �\�񒍕��ꗗ
     * @@param l_blnIsPossibleFlagSet - (is�\�t���O�ݒ�)<BR>
     * �����E����E�A�������\�t���O��<BR>
     * �ݒ肷�邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�ݒ肵�Ȃ�<BR>
     * true�F�@@�ݒ肷��<BR>
     * @@exception WEB3BaseException
     * @@roseuid 431E4E4500E1
     */
    public void createRsvOrderUnit(
        WEB3SuccOrderUnit l_requiredOrderUnit, 
        Row[] l_rsvOrderRowList, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createRsvOrderUnit(WEB3SuccOrderUnit, Row[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_requiredOrderUnit == null || l_rsvOrderRowList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //����J�����_�R���e�L�X�g�̒l��ޔ�����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        String l_strOrgMarketCode = l_clendarContext.getMarketCode();
        String l_strOrgProductCode = l_clendarContext.getProductCode();
        String l_strOrgTradingTimeType = l_clendarContext.getTradingTimeType();

        //ArrayList�𐶐�����B 
        ArrayList l_reservationOrderUnits = new ArrayList();
        //�p�����[�^.�\�񒍕�Row�ꗗ�̗v�f�����A 
        // �ȉ��̏������J��Ԃ��B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_eqtypeOrderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3GentradeInstitution l_institution = null;
        for (int i = 0; i < l_rsvOrderRowList.length; i++)
        {
            //�\�񒍕��P�ʃC���X�^���X�𐶐�����B
            WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            OrderUnit l_rsvOrderUnit = null;
            l_rsvOrderUnit = 
                l_orderManagerImpl.toOrderUnit(l_rsvOrderRowList[i]);
            
            boolean l_blnIsOrderd = false;
            long l_lngOrderUnitId = 0;
            ProductTypeEnum l_productTypeEnum = null;
            String l_strFutureOptionDiv = null;
            String l_strPriceAdjustmentValue = null;
            String l_strParentOrderId = null;
            String l_strSerialNoInParent = null;
            String l_strOrderErrorCode = null;
            String l_strMarketCode = null;
            OrderOpenStatusEnum l_rsvOrderOpenStatus = l_rsvOrderUnit.getOrderOpenStatus();
            OrderStatusEnum l_rsvOrderStatus = l_rsvOrderUnit.getOrderStatus();
            OrderCategEnum l_rsvOrderCategEnum = l_rsvOrderUnit.getOrderCateg();
            //�����Y�����R�[�h
            String l_strUnderlyingProductCode = null;
            //�o���I���敪
            String l_strExecutionEndType = null;

            if (l_rsvOrderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
            {
	            //   �������Ώۂ̗v�f�������\�񒍕��P��Row�^�ɃL���X�g����B
	            WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqTypeOrderUnit =
                    (WEB3ToSuccEqTypeOrderUnitImpl)l_rsvOrderUnit;
	            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
	                (RsvEqOrderUnitRow)l_rsvOrderUnit.getDataSourceObject();

                if (l_institution == null)
                {
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                            l_rsvEqTypeOrderUnit.getAccountId(), l_rsvEqTypeOrderUnit.getSubAccountId());
                    }
                    catch (NotFoundException l_nfe)
                    {
                        log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "�⏕�����e�[�u���ɊY������f�[�^������܂���B");
                    }
                    l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                }
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
                try
                {
                    l_strMarketCode = l_rsvEqTypeOrderUnit.getMarket().getMarketCode();
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�s��e�[�u���ɊY������f�[�^������܂���B");
                }
	                
	            // �\�񒍕������ς݃t���O
	            l_blnIsOrderd = l_rsvEqTypeOrderUnit.isOrdered();
	            // �����P��ID
                l_lngOrderUnitId = l_rsvEqOrderUnitRow.getOrderUnitId();
                // �����^�C�v
                l_productTypeEnum = l_rsvEqOrderUnitRow.getProductType();
                //�P�������l
                if (l_rsvEqTypeOrderUnit.isExecPriceOrder())
                {
                    l_strPriceAdjustmentValue =
                        WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getPriceAdjustValue());
                }
                //�e�����̒���ID = �\�񒍕��P��.�e�����̒���ID
                l_strParentOrderId = 
                    new Long(l_rsvEqOrderUnitRow.getParentOrderId()).toString();
                //�e�������A�� = �\�񒍕��P��.�e�������A��
                l_strSerialNoInParent =
                    new Long(l_rsvEqOrderUnitRow.getSerialNoInParent()).toString();
                //�����G���[���R�R�[�h = �g�����������}�l�[�W��.get�����G���[���R�R�[�h(�\�񒍕��P��.�����G���[�R�[�h)
                //�������������s�ς̏ꍇ�̂݃Z�b�g�B
                if (l_rsvEqTypeOrderUnit.isOrderExecuted())
                {
                    l_strOrderErrorCode =
                        l_eqtypeOrderManager.getErrorReasonCode(l_rsvEqOrderUnitRow.getOrderErrorCode());
                }

                //�o���I���敪�F "�o���I���i�ŏI�j"
                l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }

            //�敨OP�\�񒍕��P��Impl
            if (l_rsvOrderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            {
                //   �������Ώۂ̗v�f��敨OP�\�񒍕��P��Row�^�ɃL���X�g����
                WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                    (WEB3ToSuccIfoOrderUnitImpl)l_rsvOrderUnit;
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_rsvOrderUnit.getDataSourceObject();

                if (l_institution == null)
                {
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                                l_rsvIfoOrderUnit.getAccountId(),
                                l_rsvIfoOrderUnit.getSubAccountId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                }

                //�\�񒍕��P��.�敨�^�I�v�V�����敪 
                l_strFutureOptionDiv = l_rsvIfoOrderUnitRow.getFutureOptionDiv();

                //�s��R�[�h = �擾�����s��R�[�h
                l_strMarketCode = l_rsvIfoOrderUnit.getMarket().getMarketCode();

                // �\�񒍕������ς݃t���O
                l_blnIsOrderd = l_rsvIfoOrderUnit.isOrdered();

                // �����P��ID
                l_lngOrderUnitId = l_rsvIfoOrderUnitRow.getOrderUnitId();

                // �����^�C�v
                l_productTypeEnum = l_rsvIfoOrderUnitRow.getProductType();

                //�P�������l
                if (l_rsvIfoOrderUnit.isExecPriceOrder())
                {
                    l_strPriceAdjustmentValue =
                        WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnitRow.getPriceAdjustValue());
                }
                //�e�����̒���ID = �\�񒍕��P��.�e�����̒���ID
                l_strParentOrderId =
                    new Long(l_rsvIfoOrderUnitRow.getParentOrderId()).toString();

                //�e�������A�� = �\�񒍕��P��.�e�������A��
                l_strSerialNoInParent =
                    new Long(l_rsvIfoOrderUnitRow.getSerialNoInParent()).toString();

                WEB3OptionOrderManagerImpl l_optionOrderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                //OP�����}�l�[�W��.get�����G���[���R�R�[�h(�\�񒍕��P��.�����G���[�R�[�h)
                //�������������s�ς̏ꍇ�̂݃Z�b�g�B
                if (l_rsvIfoOrderUnit.isOrderExecuted())
                {
                    l_strOrderErrorCode =
                        l_optionOrderManager.getErrorReasonCode(l_rsvIfoOrderUnitRow.getOrderErrorCode());
                }

                //�����P��.����ID�ɊY���������.�����Y�����R�[�h
                IfoProduct l_product =
                    (IfoProduct)this.getProduct(l_rsvIfoOrderUnitRow.getProductId(), l_productTypeEnum);
                l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();

                WEB3GentradeBranch l_branch = null;
                try
                {
                    l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�o���I���敪�F�@@�g���A�J�E���g�}�l�[�W��.getBranch(�擾�������XID).is�[����{(�\�񒍕��P��.�����^�C�v)
                //== true and �\�񒍕��P��.����敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��"
                if (l_branch.isEveningSessionEnforcemented(l_productTypeEnum)
                    && WEB3Toolkit.isEquals(WEB3SessionTypeDef.OTHER, l_rsvIfoOrderUnitRow.getSessionType()))
                {
                    l_strExecutionEndType =
                        WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
                }
                //�ȊO�A"�o���I���i�ŏI�j"  
                else
                {
                    l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
                }
            }

            //�����Ώۂ̒����P��
            OrderUnit l_targetOrderUnit = null;
            EqtypeOrderUnitRow l_targetEqtypeOrderUnitRow = null;
            IfoOrderUnitRow l_targetIfoOrderUnitRow = null;

            //�����Ώۂ̒����P�ʁi=OrderUnit�j�����肷��B 
            if (l_blnIsOrderd)
            {
	            //�\�񒍕�������(�\�񒍕��P��.is������() == true)�̏ꍇ�A�e���i�̒����P�ʂ��擾����B
                //   this.get�����P��()���R�[������B
                l_targetOrderUnit = this.getOrderUnit(
                    l_lngOrderUnitId,
                    l_productTypeEnum);
                    if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                    {
                        l_targetEqtypeOrderUnitRow =
                            (EqtypeOrderUnitRow)l_targetOrderUnit.getDataSourceObject();
                    }
                    if (ProductTypeEnum.IFO.equals(l_productTypeEnum))
                    {
                        l_targetIfoOrderUnitRow =
                            (IfoOrderUnitRow)l_targetOrderUnit.getDataSourceObject();
                    }
            }
            else
            {
                l_targetOrderUnit = l_rsvOrderUnit;
            }
            
            //����J�����_�R���e�L�X�g�̒l���Z�b�g����B
            //�s��R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A"0:DEFAULT"���Z�b�g�B
            //�����R�[�h = �����Y�����R�[�h���擾���Ă���ꍇ�A
            //�擾���������Y�����R�[�h
            if (l_strUnderlyingProductCode != null)
            {
                l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                l_clendarContext.setProductCode(l_strUnderlyingProductCode);
            }
            else
            {
                //��L�ȊO�́A�擾�����s��R�[�h���Z�b�g�B
                l_clendarContext.setMarketCode(l_strMarketCode);
            }

            l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_rsvOrderCategEnum, null));

            //�\�񒍕����׃C���X�^���X�𐶐�����B
            WEB3SuccReservationOrderUnit l_succOrderUnit = 
                new WEB3SuccReservationOrderUnit();

            //this.create�A����������()���R�[������
            this.createSuccOrderUnit(
                l_succOrderUnit,
                l_targetOrderUnit,
                false);

            //���������\�񒍕����ׂɈȉ��̃v���p�e�B���Z�b�g����B 
            //���\�񒍕��P�ʂ���擾����e���ڂ́A
            //���ڐݒ肹���A��x�ʕϐ��Ɋi�[����悤�Ɏ������邱�ƁB 
            //�l�i����
            String l_strPriceCondType = null;
            if (l_blnIsOrderd && ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                l_strPriceCondType = l_targetEqtypeOrderUnitRow.getPriceConditionType();
            }
            else
            {
                l_strPriceCondType = WEB3PriceConditionDef.DEFAULT;
            }
            l_succOrderUnit.priceCondType = l_strPriceCondType;

            //���s����
            //���������敪
            String l_strExecCondType = null;
            String l_strOrderCondType = null;
            if (!l_blnIsOrderd)
            {
                l_strExecCondType = EqTypeExecutionConditionType.IntValues.NONE + "";
                l_strOrderCondType = WEB3OrderingConditionDef.DEFAULT;
            }
            else
            {
                if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                {
                    l_strExecCondType =
                        this.getExecutionConditionTypeByPr(l_targetOrderUnit);
                    l_strOrderCondType = l_targetEqtypeOrderUnitRow.getOrderConditionType();
                }
                if (ProductTypeEnum.IFO.equals(l_productTypeEnum))
                {
                    l_strExecCondType =
                        this.getExecutionConditionTypeByPr(l_targetOrderUnit);
                    l_strOrderCondType = l_targetIfoOrderUnitRow.getOrderConditionType();
                }
            }
            l_succOrderUnit.execCondType = l_strExecCondType;
            l_succOrderUnit.orderCondType = l_strOrderCondType;

            //������
            String l_strTransactionState = null;
            l_strTransactionState = this.getTransactionState(l_targetOrderUnit);

            if (l_blnIsOrderd)
            {
                l_succOrderUnit.priceAdjustmentValue = null;
            }
            else
            {
                l_succOrderUnit.priceAdjustmentValue = l_strPriceAdjustmentValue;
            }
            l_succOrderUnit.parentOrderId = l_strParentOrderId;
            l_succOrderUnit.parentOrderSequentialNo = l_strSerialNoInParent;
            l_succOrderUnit.orderErrorCode = l_strOrderErrorCode;
            l_succOrderUnit.transactionStateType = l_strTransactionState;

            //�p�����[�^.is�\�t���O�ݒ� == true�̏ꍇ
            if (l_blnIsPossibleFlagSet)
            {
                //�������̏ꍇ(�\�񒍕��P��.is������()==false)
                if (!l_blnIsOrderd)
                {
                    if (OrderOpenStatusEnum.CLOSED.equals(l_rsvOrderOpenStatus))
                    {
                        l_succOrderUnit.changeFlag = false;
                        l_succOrderUnit.cancelFlag = false;
                    }
                    try
                    {
                        WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
                            l_institution, l_productTypeEnum, l_strFutureOptionDiv, l_strExecutionEndType);
                    }
                    catch (WEB3BaseException l_e)
                    {
                        l_succOrderUnit.changeFlag = false;
                        l_succOrderUnit.cancelFlag = false;
                    }
                    if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvOrderCategEnum))
                    {
                        l_succOrderUnit.changeFlag = false;
                    }
                }
                else
                {
                    //this.set�\�t���O()���R�[������
                    this.setPossibleFlag(l_succOrderUnit, l_targetOrderUnit);
                }
            }

            //�v���p�e�B�Z�b�g�����\�񒍕�����.�A�������\�t���O = false���Z�b�g����B
            l_succOrderUnit.succOrderFlag = false;

            //ArrayList�Ƀv���p�e�B�Z�b�g�����\�񒍕����ׂ�ǉ�����B
            l_reservationOrderUnits.add(l_succOrderUnit);
            
            //�P�j�ɂđޔ������l������J�����_�R���e�L�X�g�ɍăZ�b�g����B
            l_clendarContext.setMarketCode(l_strOrgMarketCode);
            l_clendarContext.setProductCode(l_strOrgProductCode);
            l_clendarContext.setTradingTimeType(l_strOrgTradingTimeType);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
        }

        //�p�����[�^.�O�񒍕�����.�\�񒍕��ꗗ��
        //    ArrayList.toArray()�̖߂�l���Z�b�g����B 
        l_requiredOrderUnit.reservationOrderList = 
            new WEB3SuccReservationOrderUnit[l_reservationOrderUnits.size()];
        l_reservationOrderUnits.toArray(l_requiredOrderUnit.reservationOrderList);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\�t���O)<BR>
     * �����̘A���������ׂɒ����E����E�A���\�t���O��<BR>
     * �Z�b�g����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g����ȉ��̒l��<BR>
     * �@@�擾���A�ޔ�������B<BR>
     * �@@�@@��������t�g�����U�N�V����<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A<BR>
     * �@@�ȉ��̂����ꂩ�̌^�ɃL���X�g����B<BR>
     * �@@�@@�E���������P��<BR>
     * �@@�@@�E�敨OP�����P��<BR>
     * <BR>
     * �R�j�@@�L���X�g���������P�ʂ��A�ȉ��̒l���擾����B<BR>
     * �@@�@@�E�����P��.�����J�e�S��<BR>
     * �@@�@@�E�����P��.�����^�C�v<BR>
     * �@@�@@�E�����P��.���XID<BR>
     * �@@�@@�E�����P��.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�@@�E�����P��.������<BR>
     * �@@�@@�E�����P��.����ID�ɊY���������.�����Y�����R�[�h(*2)<BR>
     * �@@�@@�E�����P��.�敨�^�I�v�V�����敪(*1)<BR>
     * �@@�@@�E�����P��.����R�[�h�iSONAR�j<BR>
     * �@@�@@�E�����P��.�����o�H�敪<BR>
     * �@@�@@�E�����P��.����敪(*3)<BR>
     * <BR>
     * �@@�@@�@@(*1)���������P�ʂ̏ꍇ�A"DEFAULT"�A<BR>
     * �@@�@@�@@�@@�@@�敨OP�����P�ʂ̏ꍇ�A�����P�ʂ̐ݒ�l�Ƃ���B<BR>
     * �@@�@@�@@(*2)�敨OP�����P�ʂ̏ꍇ�̂ݎ擾�B<BR>
     * �@@�@@�@@�@@�@@this.get����(�����P��.����ID, �����P��.�����^�C�v)��<BR>
     * �@@�@@�@@�@@�@@�g�p���Ė������擾����B<BR>
     * �@@�@@�@@(*3)���������P�ʂ̏ꍇ�ANULL�A<BR>
     * �@@�@@�@@�@@�@@�敨OP�����P�ʂ̏ꍇ�A�����P�ʂ̐ݒ�l�Ƃ���B<BR>
     * <BR>
     * �S�j�@@�����^����\���ʃ`�F�b�N<BR>
     * �@@�s��ǎ��ԑ� ���� ���������ϒ����̏ꍇ<BR>
     * �@@(������ԊǗ�.is�s��J�ǎ��ԑ�() == false ���� <BR>
     *   �擾�����s�ꂩ��m�F�ς݂̐��� != NaN)<BR>
     * �@@������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������B<BR>
     * �@@��O���X���[�����ꍇ�A�p�����[�^.�A����������.�����\�t���O�A<BR>
     * �@@����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�S�|�P�j�@@�p�����[�^.�A����������.���i�敪 == "��������" or "�M�p���"�̏ꍇ <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����^�C�v�F�@@�擾���������^�C�v<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪�F�@@�擾�����敨�^�I�v�V�����敪<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�p�����[�^.�A����������.���i�敪 == "�敨" or "�I�v�V����"�̏ꍇ<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����^�C�v�F�@@�擾���������^�C�v<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪�F�@@�擾�����敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@���X�F�@@�g���A�J�E���g�}�l�[�W��.getBranch(�擾�������XID)<BR>
     * �@@�@@�@@����敪�F�@@�擾��������敪<BR>
     * �@@�@@�@@�������F�@@�擾����������<BR>
     * <BR>
     * �T�j�@@�����^����\�`�F�b�N<BR>
     * �@@�p�����[�^.�A����������.�����\�t���O == true ����<BR>
     * �@@�p�����[�^.�A����������.����\�t���O == true�̏ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * �@@�T�|�P�j�@@�p�����[�^.�A����������.���i�敪�ɂ��A<BR>
     * �@@�@@�����𕪊򂵃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.�A����������.���i�敪 ==<BR>
     * �@@�@@�@@"��������" or "�M�p���"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.validate���������\���()���\�b�h��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * �@@�@@�@@�@@��O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.�����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�A�g�����������}�l�[�W��.validate��������\���()���\�b�h��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * �@@�@@�@@�@@��O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�@@�A�A�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�����F�@@���������P��.getOrder()<BR>
     * <BR>
     * �@@�@@�@@�B�����P��.����R�[�h�iSONAR�j == "����O����"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@������ԊǗ�.validate������t�\()���R�[������B<BR>
     * �@@�@@�@@�@@��O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.����\�t���O��false���Z�b�g����B<BR>
     * �@@�@@�@@�@@���R�[������O�ɁA������t�g�����U�N�V������"���"���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�C�����P��.����R�[�h�iSONAR�j == "����O����"�A����<BR>
     * �@@�@@�@@�@@�����P��.�����o�H�敪 == "HOST"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.�A����������.���i�敪 ==<BR>
     * �@@�@@�@@"�敨" or "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@OP�����}�l�[�W��.validate���������\���()���\�b�h��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * �@@�@@�@@�@@��O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.�����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�AOP�����}�l�[�W��.validate��������\���()���\�b�h��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * �@@�@@�@@�@@��O���X���[�����ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.�A����������.����\�t���O��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�@@�A�A�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�����F�@@�敨OP�����P��.getOrder()<BR>
     * <BR>
     * �U�j�@@�A�������\�`�F�b�N<BR>
     * �@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�p�����[�^.�A����������.�A�������\�t���O��false���Z�b�g����B<BR>
     * �@@�@@�E�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e����()��<BR>
     * �@@�@@�@@��O���X���[<BR>
     * �@@�@@�E������ԊǗ�.validate�A��������t�\()����O���X���[<BR>
     * �@@�@@�E�A�������}�l�[�W��Impl.validate�A�������ő�ݒ萔()��<BR>
     * �@@�@@�@@��O���X���[<BR>
     * <BR>
     * �@@�@@�@@[validate�g���K�[�����ݒ�To�e����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�e�����̒����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �@@�@@�@@[validate�A��������t�\()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�،���ЁF�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h�ɊY������،����
     * �@@�@@�@@�@@�����^�C�v�F�@@�擾���������^�C�v<BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@�擾�����敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�@@�o���I���敪�F<BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.�A����������.���i�敪 == "��������" or "�M�p���"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@"�o���I���i�ŏI�j"<BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.�A����������.���i�敪 == "�敨" or "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�擾�������XID).is�[����{(�擾���������^�C�v) == true and<BR>
     * �@@�@@�@@�@@�@@�@@�擾��������敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"�o���I���i�ŏI�j"<BR>
     * <BR>
     * �@@�@@�@@[validate�A�������ő�ݒ萔()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�e�����̒����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �V�j�@@�P�j�ɂđޔ������l������J�����_�R���e�L�X�g��<BR>
     * �@@�ăZ�b�g����B<BR>
     * @@param l_succOrderUnit - (�A����������)<BR>
     * �A���������׃I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@exception WEB3BaseException
     * @@roseuid 43254BFB008B
     */
    public void setPossibleFlag(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "setPossibleFlag(WEB3SuccOrderUnit, OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_succOrderUnit == null || l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@����J�����_�R���e�L�X�g����ȉ��̒l�� 
        // �擾���A�ޔ�������B 
        // ��������t�g�����U�N�V����
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //������t�g�����U�N�V����
        String l_strOldOrderAcceptTransaction = l_clendarContext.getOrderAcceptTransaction();

        //�Q�j�@@�p�����[�^.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A 
        //�ȉ��̂����ꂩ�̌^�ɃL���X�g����B 
        //�E���������P�� 
        //�E�敨OP�����P��

        //�R�j�@@�L���X�g���������P�ʂ��A�ȉ��̒l���擾����B
        //�����P��.�����J�e�S��  
        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        //�����P��.�����^�C�v
        ProductTypeEnum l_productType = null;
        //�����P��.�s��ID�ɊY������s��.�s��R�[�h
        String l_strMarketCode = null;
        //�����P��.������
        Date l_datOrderBizDate = null;
        //�����P��.����ID�ɊY���������.�����Y�����R�[�h
        //�敨OP�����P�ʂ̏ꍇ�̂ݎ擾
        String l_strUnderlyingProductCode = null;
        //�����P��.�敨�^�I�v�V�����敪
        String l_strFutureOptionDiv = null;
        //�����P��.����R�[�h�iSONAR�j
        String l_strSonarTradedCode = null;
        //�����P��.�����o�H�敪
        String l_strOrderRouteDiv = null;
        //�����P��.���XID 
        long l_lngBranchId = 0;
        //�����P��.����敪
        String l_strSessionType = null;

        try
        {
            if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                    (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();
                //�����P��.�����^�C�v
                l_productType = l_eqTypeOrderUnitRow.getProductType();
                //�����P��.���XID
                l_lngBranchId = l_eqTypeOrderUnitRow.getBranchId();
                //�����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
                //�敨�^�I�v�V�����敪�͊��������P�ʂ̏ꍇ�A"DEFAULT"
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
                //�����P��.����R�[�h�iSONAR�j
                l_strSonarTradedCode = l_eqTypeOrderUnitRow.getSonarTradedCode();
                //�����P��.�����o�H�敪
                l_strOrderRouteDiv = l_eqTypeOrderUnitRow.getOrderRootDiv();
				//�����P��.������
				l_datOrderBizDate = 
					WEB3DateUtility.getDate(
						l_eqTypeOrderUnitRow.getBizDate(),
						WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                //�����P��.����敪
                //���������P�ʂ̏ꍇ�ANULL
                l_strSessionType = null;
            }
            else if (l_orderUnit instanceof IfoOrderUnit)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow)((IfoOrderUnit)l_orderUnit).getDataSourceObject();
                //�����P��.�����^�C�v
                l_productType = l_ifoOrderUnitRow.getProductType();
                //�����P��.���XID
                l_lngBranchId = l_ifoOrderUnitRow.getBranchId();
                //�����P��.�s��ID�ɊY������s��.�s��R�[�h
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_ifoOrderUnitRow.getMarketId()).getMarketCode();
                //�����P��.����ID�ɊY���������.�����Y�����R�[�h
                IfoProduct l_product = 
                    (IfoProduct)this.getProduct(l_ifoOrderUnitRow.getProductId(), l_productType);
                l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();
                //�敨�^�I�v�V�����敪�͐敨OP�����P�ʂ̏ꍇ�A�����P�ʂ̐ݒ�l�Ƃ���
                l_strFutureOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
                //�����P��.����R�[�h�iSONAR�j
                l_strSonarTradedCode = l_ifoOrderUnitRow.getSonarTradedCode();
                //�����P��.�����o�H�敪
                l_strOrderRouteDiv = l_ifoOrderUnitRow.getOrderRootDiv();
                //�����P��.������
                l_datOrderBizDate = 
                    WEB3DateUtility.getDate(
                        l_ifoOrderUnitRow.getBizDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                //�����P��.����敪
                //�敨OP�����P�ʂ̏ꍇ�A�����P�ʂ̐ݒ�l�Ƃ���
                l_strSessionType = l_ifoOrderUnitRow.getSessionType();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        
        //�S�j�@@�����^����\���ʃ`�F�b�N
        //�����P�ʂ̔����������ݓ������Z�o�������������O�̏ꍇ
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //�g���A�J�E���g�}�l�[�W��.getBranch(�擾�������XID)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
        {
            try
            {
                //�S�|�P�j�@@�p�����[�^.�A����������.���i�敪 == "��������" or "�M�p���"�̏ꍇ
                if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType)
                    || WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
                {
                    //������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������
                    WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                        l_productType, l_strFutureOptionDiv);
                }
                //�S�|�Q�j�@@�p�����[�^.�A����������.���i�敪 == "�敨" or "�I�v�V����"�̏ꍇ
                else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType)
                    || WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
                {
                    //[����]
                    //�����^�C�v�F�@@�擾���������^�C�v
                    //�敨�^�I�v�V�����敪�F�@@�擾�����敨�^�I�v�V�����敪
                    //���X�F�@@�g���A�J�E���g�}�l�[�W��.getBranch(�擾�������XID)
                    //����敪�F�@@�擾��������敪
                    //�������F�@@�擾����������
                    WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                        l_productType,
                        l_strFutureOptionDiv,
                        l_branch,
                        l_strSessionType,
                        l_datOrderBizDate);
                }

            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("validate�ǌ���������t�\()���R�[�����鎞�A��O���X���[����");
                l_succOrderUnit.changeFlag = false;
                l_succOrderUnit.cancelFlag = false;
            }
        }

        //�T�j�@@�����^����\�`�F�b�N 
        if (l_succOrderUnit.changeFlag && l_succOrderUnit.cancelFlag)
        {
            //�@@�T�|�P�j�@@�p�����[�^.�A����������.���i�敪�ɂ��A 
            //  �����𕪊򂵃`�F�b�N���s���B
            if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType) ||
				WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
            {
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                try
                {
                    l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate���������\���()���R�[�����鎞�A��O���X���[����");
                    l_succOrderUnit.changeFlag = false;
                }
                try
                {
                    l_orderManager.validateOrderForCancellation(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate��������\���()���R�[�����鎞�A��O���X���[����");
                    l_succOrderUnit.cancelFlag = false;
                }
                if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode))
                {
                    l_clendarContext.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
                    try
                    {
                        WEB3GentradeTradingTimeManagement.validateOrderAccept();
                    }
                    catch (WEB3BaseException l_wbe)
                    {
                        log.debug("����O�����E��t���ԊO");
                        l_succOrderUnit.cancelFlag = false;
                    }
                }
                if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode) &&
                    WEB3OrderRootDivDef.HOST.equals(l_strOrderRouteDiv))
                {
                    log.debug("HOST���̗͂���O��������");
                    l_succOrderUnit.cancelFlag = false;
                }
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType) ||
				WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
            {
                WEB3OptionOrderManagerImpl l_orderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                try
                {
                    l_orderManager.validateOrderChangePossibleStatus(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException L_wbe)
                {
                    log.debug("validate���������\���()���R�[�����鎞�A��O���X���[����");
                    l_succOrderUnit.changeFlag = false;
                }
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate��������\���()���R�[�����鎞�A��O���X���[����");
                    l_succOrderUnit.cancelFlag = false;
                }
            }
        }

        //�U�j�@@�A�������\�`�F�b�N
        try
        {
            WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            //�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e����()����O���X���[ 
            l_orderManagerImpl.validateTriggerOrderSettingToParentOrder(l_orderUnit);
            //������ԊǗ�.validate�A��������t�\()����O���X���[    
            String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution(l_strInstitutionCode);

            //�o���I���敪�F
            String l_strExecutionEndType = null;
            //�p�����[�^.�A����������.���i�敪 == "��������" or "�M�p���"�̏ꍇ
            if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType)
                || WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
            {
                //"�o���I���i�ŏI�j"
                l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType)
                || WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
            {
                //�g���A�J�E���g�}�l�[�W��.getBranch(�擾�������XID).is�[����{(�擾���������^�C�v) == true and
                //�擾��������敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��"
                if (l_branch.isEveningSessionEnforcemented(l_productType)
                    && WEB3Toolkit.isEquals(WEB3SessionTypeDef.OTHER, l_strSessionType))
                {
                    l_strExecutionEndType =
                        WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
                }
                else
                {
                    //�ȊO�A"�o���I���i�ŏI�j"
                    l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
                }
            }

            WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
                l_institution,
                l_productType,
                l_strFutureOptionDiv,
                l_strExecutionEndType);
            //�A�������}�l�[�W��Impl.validate�A�������ő�ݒ萔()����O���X���[
            l_orderManagerImpl.validateSuccOrderMaxQuantity(l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            l_succOrderUnit.succOrderFlag = false;
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //�V�j�@@�P�j�ɂđޔ������l������J�����_�R���e�L�X�g�� 
        //      �ăZ�b�g����B
        l_clendarContext.setOrderAcceptTransaction(l_strOldOrderAcceptTransaction);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �ڋq���ێ�����\�񒍕��̈ꗗ��ԋp����B<BR>
     * <BR>
     * [�߂�l��Hashtable�̍\��]<BR>
     *  - key�F�@@�e�����̒���ID + �����^�C�v<BR>
     *  - value�F�@@ArrayList�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����"�e�����̒���ID"�����\�񒍕��P�ʂ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@"�e�����A��" �����Ń\�[�g�������R�[�h���i�[�B<BR>
     * <BR>
     * �P�j�@@Hashtable�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�\�񒍕����擾����B<BR>
     * �@@�Q�|�P�j�@@����.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̏����ɂāA�����\�񒍕��P�ʃe�[�u������������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID = �p�����[�^.����ID<BR>
     * <BR>
     * �@@�@@[�\�[�g]<BR>
     * �@@�@@�@@�e�����̒���ID ����,<BR>
     * �@@�@@�@@�e�������A�� ����<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����.���i�敪�ꗗ��"�敨" or "�I�v�V����"���܂܂��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̏����ɂāA�敨OP�\�񒍕��P�ʃe�[�u������������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID = �p�����[�^.����ID<BR>
     * �@@�@@[�\�[�g]<BR>
     * �@@�@@�@@�e�����̒���ID ����,<BR>
     * �@@�@@�@@�e�������A�� ����<BR>
     * <BR>
     * �R�j�@@�Q�j�̖߂�l != null�̏ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@�R�|�P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�Q�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�R�|�Q�|�P�j�@@ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�@@�R�|�Q�|�R�j�ȍ~�̏��������{����B<BR>
     * �@@�@@�@@�@@���ݏ������Ă���v�f���Ō�̗v�f�̏ꍇ<BR>
     * �@@�@@�@@�A�����Ώۂ̗v�f.�e�����̒���ID + �����Ώۂ̗v�f.�����^�C�v != <BR>
     * �@@�@@�@@�@@���̗v�f(index + 1).�e�����̒���ID + �����Ώۂ̗v�f.�����^�C�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�R�j�@@Hashtable.put()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�����Ώۂ̗v�f.�e�����̒���ID +<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̗v�f.�����^�C�v<BR>
     * �@@�@@�@@�@@value�F�@@ArrayList<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�S�j�@@ArrayList.clear()���\�b�h���R�[������B<BR>
     * <BR>
     * �S�j�@@Hashtable��ԋp����B<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID
     * @@param l_strCommodityTypeList - (���i�敪�ꗗ)<BR>
     * ���i�敪�ꗗ
     * @@return Hashtable
     * @@exception WEB3BaseException
     * @@roseuid 431F76F500DF
     */
    public Hashtable getRsvOrderUnitList(long l_lngAccountId, String[] l_strCommodityTypeList) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getRsvOrderUnitList(long, String[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@Hashtable�𐶐�����B
        Hashtable l_htRsvOrderUnit = new Hashtable();
        
        //[����] 
        //  ����ID = �p�����[�^.����ID 
        //  [�\�[�g] 
        //  �e�����̒���ID ����, 
        //  �e�������A�� ����
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");

        List l_lstWhere = new ArrayList();
        l_lstWhere.add(new Long(l_lngAccountId));
        Object[] l_objWhere = new Object[l_lstWhere.size()];
        l_lstWhere.toArray(l_objWhere);

        String l_strOrderBy = " parent_order_id asc, serial_no_in_parent asc ";
        List l_lstRecords = null;

        boolean l_blnIsEq = false;
        boolean l_blnIsIfo = false;
        try
        {
            int l_intCommodityTypeCnt = 0;
            if (l_strCommodityTypeList !=null)
            {
                l_intCommodityTypeCnt = l_strCommodityTypeList.length;
            }

            for (int i = 0; i < l_intCommodityTypeCnt; i++)
            {
                //�Q�j�\�񒍕����擾����B
                //�Q�|�P�j�@@����.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ
                //�����\�񒍕��P�ʃe�[�u������������
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityTypeList[i])
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityTypeList[i]))
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        RsvEqOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objWhere);

                    l_blnIsEq = true;
                    break;
                }
                
                //�@@�Q�|�Q�j�@@����.���i�敪�ꗗ��"�敨" or "�I�v�V����"���܂܂��ꍇ�A
                //�敨OP�\�񒍕��P�ʃe�[�u������������B
                else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeList[i])
                    || WEB3CommodityDivDef.OPTION.equals(l_strCommodityTypeList[i]))
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        RsvIfoOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objWhere);

                    l_blnIsIfo = true;
                    break;
                }
            }
            

        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //�Q�|�P�j�̖߂�l == null�̏ꍇ
        if (l_lstRecords == null || l_lstRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            //�S�j�@@Hashtable��ԋp����
            return l_htRsvOrderUnit;
        }

        //�R�j�@@�Q�j�̖߂�l != null�̏ꍇ�A�ȉ��̏��������{����
        //�R�|�P�jArrayList�𐶐�����B 
        ArrayList l_records = new ArrayList();
        //�R�|�Q�j�Q�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_lstRecords.size(); i ++)
        {
            //�R�|�Q�|�P�j�@@ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B
            l_records.add(l_lstRecords.get(i));

            //�R�|�Q�|�Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ
            //�@@���ݏ������Ă���v�f���Ō�̗v�f�̏ꍇ
            //�A�����Ώۂ̗v�f.�e�����̒���ID + �����Ώۂ̗v�f.�����^�C�v !=  
            //  ���̗v�f(index + 1).�e�����̒���ID + �����Ώۂ̗v�f.�����^�C�v�̏ꍇ
            String l_strKey = null;
            String l_strNextKey = null;

            if (l_blnIsEq)
            {
                l_strKey =
                    WEB3TriggerOrderUtility.createKey(
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i)).getParentOrderId(),
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i)).getProductType());
            }
            else if (l_blnIsIfo)
            {
                l_strKey =
                    WEB3TriggerOrderUtility.createKey(
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i)).getParentOrderId(),
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i)).getProductType());
            }

            l_strNextKey = l_strKey;
            if (i < (l_lstRecords.size() - 1))
            {
                if (l_blnIsEq)
                {
                    l_strNextKey = WEB3TriggerOrderUtility.createKey(
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i + 1)).getParentOrderId(),
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i + 1)).getProductType());
                }
                else if (l_blnIsIfo)
                {
                    l_strNextKey = WEB3TriggerOrderUtility.createKey(
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i + 1)).getParentOrderId(),
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i + 1)).getProductType());
                }
            }

            if ((i == l_lstRecords.size() - 1) || (!l_strKey.equals(l_strNextKey)))
            {
                //�R�|�Q�|�R�j�@@Hashtable.put()���\�b�h���R�[������B 
                //  [put()�Ɏw�肷�����] 
                //key�F�@@�����Ώۂ̗v�f.�e�����̒���ID + 
                //�����Ώۂ̗v�f.�����^�C�v 
                //value�F�@@ArrayList             }
                ArrayList l_groupRecords = new ArrayList();
                l_groupRecords.addAll(l_records);
                
                l_htRsvOrderUnit.put(l_strKey, l_groupRecords);
                
                //�R�|�Q�|�S�j�@@ArrayList.clear()���\�b�h���R�[������B
                l_records.clear();
            }
        }

        log.exiting(STR_METHOD_NAME);
        //�S�j�@@Hashtable��ԋp����
        return l_htRsvOrderUnit;
    }

    /**
     * (get���i�敪)<BR>
     * �����̒�����ʂ�菤�i�敪�𔻕ʂ��A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �p�����[�^.������ʂ��A<BR>
     * �@@["����������" or "����������"�̏ꍇ]<BR>
     * �@@�@@"��������"��ԋp����B<BR>
     * <BR>
     * �@@["�M�p�V�K��������" or<BR>
     * �@@�@@"�M�p�V�K��������" or<BR>
     * �@@�@@"�M�p�����ԍϒ����i���ԍρj" or<BR>
     * �@@�@@"�M�p�����ԍϒ����i���ԍρj" or<BR>
     * �@@�@@"�M�p��������" or<BR>
     * �@@�@@"�M�p���n����"�̏ꍇ]<BR>
     * �@@�@@"�M�p���"��ԋp����B<BR>
     * <BR>
     * �@@["�敨�V�K��������" or<BR>
     * �@@�@@"�敨�V�K��������" or<BR>
     * �@@�@@"�敨�������ԍϒ����i���ԍρj" or<BR>
     * �@@�@@"�敨�������ԍϒ����i���ԍρj"�̏ꍇ]<BR>
     * �@@�@@"�敨"��ԋp����B<BR>
     * <BR>
     * �@@["OP�V�K��������" or<BR>
     * �@@�@@"OP�V�K��������" or<BR>
     * �@@�@@"OP�������ԍϒ����i���ԍρj" or<BR>
     * �@@�@@"OP�������ԍϒ����i���ԍρj"�̏ꍇ]<BR>
     * �@@�@@"�I�v�V����"��ԋp����B<BR>
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 431F90BF00DF
     */
    public String getCommodityDiv(OrderTypeEnum l_orderType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommodityDiv(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strCommodityDiv = null;

        //["����������" or "����������"�̏ꍇ
        if ((OrderTypeEnum.EQUITY_BUY.equals(l_orderType)) ||
            (OrderTypeEnum.EQUITY_SELL.equals(l_orderType)))
        {
            //"��������"��ԋp����
            l_strCommodityDiv = WEB3CommodityDivDef.EQUITY;
        }
        //"�M�p�V�K��������" or 
        //"�M�p�V�K��������" or 
        //"�M�p�����ԍϒ����i���ԍρj" or 
        //"�M�p�����ԍϒ����i���ԍρj" or 
        //"�M�p��������" or 
        //"�M�p���n����"�̏ꍇ
        else if ((OrderTypeEnum.MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType)) ||
            (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType)) ||
            (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType)))
        {
            //"�M�p���"��ԋp����
            l_strCommodityDiv = WEB3CommodityDivDef.MARGIN;
        }
        //"�敨�V�K��������" or 
        //"�敨�V�K��������" or 
        //"�敨�������ԍϒ����i���ԍρj" or 
        //"�敨�������ԍϒ����i���ԍρj"�̏ꍇ
        else if ((OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType)))
        {
            //"�敨"��ԋp����
            l_strCommodityDiv = WEB3CommodityDivDef.FUTURE;
        }
        //"OP�V�K��������" or 
        //"OP�V�K��������" or 
        //"OP�������ԍϒ����i���ԍρj" or 
        //"OP�������ԍϒ����i���ԍρj"�̏ꍇ
        else if ((OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType)))
        {
            //"�I�v�V����"��ԋp����
            l_strCommodityDiv = WEB3CommodityDivDef.OPTION;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strCommodityDiv;
    }

	/**
	 * �iget���i�敪�̃I�[�o�[���[�h�j <BR>
	 * �����̘A����������敪�ɊY�����鏤�i�敪�𔻕ʂ��A <BR>
	 * �ԋp����B <BR>
	 * <BR>
	 * �p�����[�^.�A����������敪���A <BR>
	 * �@@["���t�i�O�񒍕��j" or "���t" <BR>
	 * �@@�@@or "���t�i�O�񒍕��j" or "���t�i�����c�j"�̏ꍇ] <BR>
	 * �@@�@@"��������"��ԋp����B <BR>
	 * <BR>
	 * �@@["�M�p�V�K���i�O�񒍕��j" or <BR>
	 * �@@�@@"�M�p�V�K��" or <BR>
	 * �@@�@@"�M�p�ԍρi�O�񒍕��j" or <BR>
	 * �@@�@@"�M�p�ԍρi�����c�j" or <BR>
	 * �@@�@@"�������n�i�O�񒍕��j" or <BR>
	 * �@@�@@"�������n�i�����c�j"�̏ꍇ] <BR>
	 * �@@�@@"�M�p���"��ԋp����B <BR>
	 * <BR>
	 * �@@["�敨�V�K���i�O�񒍕��j" or <BR>
	 * �@@�@@"�敨�V�K��" or <BR>
	 * �@@�@@"�敨�ԍρi�O�񒍕��j" or <BR>
	 * �@@�@@"�敨�ԍρi�����c�j"�̏ꍇ] <BR>
	 * �@@�@@"�敨"��ԋp����B <BR>
	 * <BR>
	 * �@@["OP�V�K���i�O�񒍕��j" or <BR>
	 * �@@�@@"OP�V�K��" or <BR>
	 * �@@�@@"OP�ԍρi�O�񒍕��j" or <BR>
	 * �@@�@@"OP�ԍρi�����c�j"�̏ꍇ] <BR>
	 * �@@�@@"OP"��ԋp����B <BR>
	 * @@param succTradingTypeList - (�A����������敪�ꗗ)<BR>
	 * �A����������敪�ꗗ
	 * @@return String
	 * @@exception WEB3BaseException
	 * @@roseuid 431F90BF00DF
	 */
	public String getCommodityDiv(String succTradingTypeList) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getCommodityDiv(succTradingTypeList)";
		log.entering(STR_METHOD_NAME);

		if (succTradingTypeList == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		String l_strCommodityDiv = null;

		//"���t�i�O�񒍕��j" or 
		//"���t" or 
		//"���t�i�O�񒍕��j" or 
		//"���t�i�����c�j" �̏ꍇ
		if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.BUY.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"��������"��ԋp����
			l_strCommodityDiv = WEB3CommodityDivDef.EQUITY;
		}
		//"�M�p�V�K���i�O�񒍕��j" or
		//"�M�p�V�K��" or
		//"�M�p�ԍρi�O�񒍕��j" or
		//"�M�p�ԍρi�����c�j" or
		//"�������n�i�O�񒍕��j" or
		//"�������n�i�����c�j"�̏ꍇ
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"�M�p���"��ԋp����
			l_strCommodityDiv = WEB3CommodityDivDef.MARGIN;
		}
		//"�敨�V�K���i�O�񒍕��j" or 
		//"�敨�V�K��" or 
		//"�敨�ԍρi�O�񒍕��j" or 
		//"�敨�ԍρi�����c�j"�̏ꍇ
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"�敨"��ԋp����
			l_strCommodityDiv = WEB3CommodityDivDef.FUTURE;
		}
		
		//"OP�V�K���i�O�񒍕��j" or 
		//"OP�V�K��" or 
		//"OP�ԍρi�O�񒍕��j" or 
		//"OP�ԍρi�����c�j"�̏ꍇ
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"�I�v�V����"��ԋp����
			l_strCommodityDiv = WEB3CommodityDivDef.OPTION;
		}

		log.exiting(STR_METHOD_NAME);
		return l_strCommodityDiv;
	}

    /**
     * �iget��t���ԋ敪�j <BR>
     * �����̒����J�e�S������������ԃe�[�u��read�Ɏg�p�����t���ԋ敪�𔻕ʂ��A<BR>
     * �ԋp����B <BR>
     * <BR>
     * �p�����[�^.�����J�e�S���A����R�[�h�iSONAR�j�̒l�ɂ��A�ȉ��̒ʂ�ԋp����B<BR>
     * �@@�@@["�����E���n����"�̏ꍇ]<BR>
     * �@@�@@�@@"�����E���n"��ԋp�B<BR>
     * �@@�@@["��������"�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.����R�[�h�iSONAR�j=="����O����"�̏ꍇ�́A"����O����"��ԋp�B<BR>
     * �@@�@@�@@��L�ȊO�́A"���E�M�p"��ԋp�B<BR>
     * �@@�@@["�V�K������" or "�ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�@@"���E�M�p"��ԋp�B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@"�����w���敨OP"��ԋp�B<BR>
     * @@param l_orderCateg - (�����J�e�S��)<BR>
     * �����J�e�S��<BR>
     * @@param l_strSonarTradedCode - (����R�[�h�iSONAR�j)<BR>
     * ����R�[�h�iSONAR�j<BR>
     * @@return String
     * @@exception WEB3SystemLayerException
     * @@roseuid 431F90BF00DF
     */
    public String getTradingTimeType(OrderCategEnum l_orderCateg, String l_strSonarTradedCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingTimeType(OrderCategEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderCateg == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strOrderCateg = null;
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.SWAP;
        }
        else if (OrderCategEnum.ASSET.equals(l_orderCateg))
        {
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode))
            {
                l_strOrderCateg = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
            }
            else
            {
                l_strOrderCateg = WEB3TradingTimeTypeDef.EQUITY;
            }
        }
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg) ||
            OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.EQUITY;
        }
        else
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.INDEX_FUTURE_OP;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strOrderCateg;
    }

    /**
     * (get����)<BR>
     * �����̏،���ЁA�����R�[�h�A���i�敪��<BR>
     * �Y������������擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���i�敪�ɂ��A�����̎擾���@@��<BR>
     * �@@���򂷂�B<BR>
     * �@@[�p�����[�^.���i�敪 == ("��������" or "�M�p���")�̏ꍇ]<BR>
     * �@@�@@�g���v���_�N�g�}�l�[�W��.getProduct()�ɂĊ����������擾���A<BR>
     * �@@�@@�ԋp����B<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�敨OP�v���_�N�g�}�l�[�W��.get����()�ɂĐ敨OP������<BR>
     * �@@�@@�擾���A�ԋp����B<BR>
     * <BR>
     * �@@[getProduct()�Aget����()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�،����<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�����R�[�h<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 4325512901D3
     */
    public Product getProduct(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strCommodityDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getProduct(WEB3GentradeInstitution, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strProductCode == null || l_strCommodityDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Product l_product = null;

        try
        {
            //�p�����[�^.���i�敪 == ("��������" or "�M�p���")�̏ꍇ
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) ||
                (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv)))
            {
                //�g���v���_�N�g�}�l�[�W��.getProduct()�ɂĊ����������擾���A 
                //�ԋp����B 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);      
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_institution, l_strProductCode);
            }
            //��L�ȊO�̏ꍇ
            else
            {
                //�敨OP�v���_�N�g�}�l�[�W��.get����()�ɂĐ敨OP������ 
                //�擾���A�ԋp����B             
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
                WEB3IfoProductManagerImpl l_productMgr = 
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                l_product = l_productMgr.getIfoProduct(l_institution, l_strProductCode);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h:[" + l_strProductCode + "]�ɊY�����銔���������擾�ł��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_product;
    }

    /**
     * (get����)<BR>
     * �����̖����^�C�v�A����ID�ɊY�����������<BR>
     * �擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����^�C�v�ɂ��A�����̎擾���@@��<BR>
     * �@@���򂷂�B<BR>
     * �@@[�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ]<BR>
     * �@@�@@�g���v���_�N�g�}�l�[�W��.getProduct()�ɂĊ����������擾���A<BR>
     * �@@�@@�ԋp����B<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�敨OP�v���_�N�g�}�l�[�W��.getProduct()�ɂĐ敨OP������<BR>
     * �@@�@@�擾���A�ԋp����B<BR>
     * <BR>
     * �@@[�S�Ă�getProduct()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.����ID<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 431FA2A9036F
     */
    public Product getProduct(
        long l_lngProductId, 
        ProductTypeEnum l_productType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProduct(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        Product l_product = null;

        if (l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                //�g���v���_�N�g�}�l�[�W��.getProduct()�ɂĊ����������擾���A 
                //�ԋp����B 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);      
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_lngProductId);
            }
            //��L�ȊO�̏ꍇ
            else
            {
                //�敨OP�v���_�N�g�}�l�[�W��.getProduct()�ɂĐ敨OP������ 
                //�擾���A�ԋp����B 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
                WEB3IfoProductManagerImpl l_productMgr = 
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_lngProductId);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID:[" + l_lngProductId + "]�ɊY�����銔���������擾�ł��܂���B",
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_product;
    }

    /**
     * (get���s�����iPR�w�j)<BR>
     * �����̎��s�����ɊY�����鎷�s�����iPR�w�j��ԋp����B<BR>
     * <BR>
     * �P�j�@@�^�̔���<BR>
     * �@@�p�����[�^.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A<BR>
     * �@@�ȉ��̂����ꂩ�ɃL���X�g����B<BR>
     * �@@�E���������P��<BR>
     * �@@�E�敨OP�����P��<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ<BR>
     * �@@�Q�|�P�j�@@���������P�ʂɃL���X�g����B <BR>
     * �@@�Q�|�Q�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get���s�����iSONAR�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@���s�����F�@@���������P��.���s���� <BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ<BR>
     * �@@�R�|�P�j�@@�敨OP�����P�ʂɃL���X�g����B <BR>
     * �@@�R�|�Q�j�@@�敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get���s�����iPR�w�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@���s�����F�@@�敨OP�����P��.���s����<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�� 
     * @@return String
     * @@exception WEB3BaseException
     */
    public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionTypeByPr(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    �P�j�@@�^�̔���
        //   �@@�p�����[�^.�����P�ʂ̌^��instanceof�ɂĔ��ʂ�
        //   �@@�ȉ��̂����ꂩ�ɃL���X�g����B
        //   �@@�E���������P��
        //   �@@�E�敨OP�����P��
        if (!(l_orderUnit instanceof EqTypeOrderUnit)
            && !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.debug("�p�����[�^.�����P�ʂ̌^�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //   �Q�j�@@�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ
        //   �@@�Q�|�P�j�@@���������P�ʂɃL���X�g����B
        //   �@@�Q�|�Q�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���R�[����
        //   �@@�@@�@@�@@�@@�@@�߂�l��ԋp����B
        //   �@@�@@�@@�@@�@@�@@[get���s�����iSONAR�j()�Ɏw�肷�����]
        //   �@@�@@�@@�@@�@@�@@���s�����F�@@���������P��.���s����

        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            String l_strExecCondTypeSonar =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_eqtypeOrderUnitRow.getExecutionConditionType());

            log.exiting(STR_METHOD_NAME);
            return l_strExecCondTypeSonar;
        }

        //
        //   �R�j�@@�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ
        //   �@@�R�|�P�j�@@�敨OP�����P�ʂɃL���X�g����B
        //   �@@�R�|�Q�j�@@�敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j()���R�[����
        //   �@@�@@�@@�@@�@@�@@�߂�l��ԋp����
        //   �@@�@@�@@�@@�@@�@@[get���s�����iPR�w�j()�Ɏw�肷�����]
        //   �@@�@@�@@�@@�@@�@@���s�����F�@@�敨OP�����P��.���s����
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            String l_strExecutionCondByPr =
                WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getExecutionConditionType());

            log.exiting(STR_METHOD_NAME);
            return l_strExecutionCondByPr;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get������)<BR>
     * �����P�ʂɊY�����鏈���󋵋敪��ԋp����B<BR>
     * <BR>
     * �@@�������\�b�h���ĂԍہA�ȉ��_�ɒ��ӂ���B<BR>
     * �@@�@@�@@�\�񒍕����h�����ρh�̏ꍇ�A���������P��Impl�A<BR>
     * �@@�@@�@@�܂��͐敨OP�����P��Impl���p�����[�^�ɐݒ肷��B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʂ̌^���\�񒍕��P�ʂ̏ꍇ�A<BR> 
     *�@@�@@�@@�@@�ȉ��̏��������{����B<BR> 
     *�@@�Q�|�P�j�@@�ȉ������ɊY������߂�l��ԋp����B<BR> 
     *�@@�@@�@@�@@�@@�@@�\�񒍕��P��.�����L����� == "�N���[�Y"�̏ꍇ<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�\�񒍕��P��.������� == "�����ρi��������j"�̏ꍇ<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"0003�F�S���������"���Z�b�g<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"3020�F����"���Z�b�g<BR> 
     *�@@�@@�@@�@@�@@�@@��L�ȊO<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@"3000�F�\��"���Z�b�g<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ�A<BR>
     * �@@���������P�ʂɃL���X�g���A�ȉ��̏��������{����B<BR>
     * �@@�@@�Q�|�P�j�@@�����f�[�^�A�_�v�^.get�����󋵋敪()<BR> 
     *�@@�@@�@@�@@�@@�@@�@@���\�b�h���R�[�����A�߂�l��ԋp����B<BR> 
     * <BR>
     * �R�j�@@�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ�A<BR>
     * �@@�敨OP�����P�ʂɃL���X�g���A�ȉ��̏��������{����B<BR>
     * �@@�@@�R�|�P�j�@@�敨OP�f�[�^�A�_�v�^.get�����󋵋敪()<BR> 
     *�@@�@@�@@�@@�@@�@@�@@���\�b�h���R�[�����A�߂�l��ԋp����B<BR> 

     * <BR> 
     * �@@[�S�Ă�get�����󋵋敪()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�L���X�g���������P��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 431FAAC70321
     */
    public String getTransactionState(OrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactionState(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!(l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl) &&
            !(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strTransactionState = null;

        //�P�j�@@�p�����[�^.�����P�ʂ̌^���\�񒍕��P�ʂ̏ꍇ
        if (l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl
            || l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
        {
            //�\�񒍕��P��.�����L����� == "�N���[�Y"�̏ꍇ
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
            {
                //�\�񒍕��P��.������� == "�����ρi��������j"�̏ꍇ
                if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
                {
                    //"0003�F�S���������"���Z�b�g
                    l_strTransactionState = WEB3ToSuccTransactionStateDef.ALL_CANCELED;
                }
                else
                {
                    //"3020�F����"���Z�b�g 
                    l_strTransactionState = WEB3ToSuccTransactionStateDef.INEFFECTIVE;
                }
            }
            else
            {
                //"3000�F�\��"���Z�b�g
                l_strTransactionState = WEB3ToSuccTransactionStateDef.RESERVATION;
            }            
        }

        //�Q�j�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ
        else if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            //�Q�|�P�j�@@�����f�[�^�A�_�v�^.get�����󋵋敪()
            //          ���\�b�h���R�[�����A�߂�l��ԋp����B
            l_strTransactionState = 
                WEB3EquityDataAdapter.getTransactionStatusType((EqTypeOrderUnit)l_orderUnit);
            
        }

        //�R�j�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            //�R�|�P�j�@@�敨OP�f�[�^�A�_�v�^.get�����󋵋敪()
            //          ���\�b�h���R�[�����A�߂�l��ԋp����B
            l_strTransactionState = WEB3IfoDataAdapter.getTransactionStatusType((IfoOrderUnit)l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTransactionState;
    }

    /**
     * (get�����P��)<BR>
     * �����̒����P��ID�A�����^�C�v�ɊY�����钍���P�ʂ�<BR>
     * �擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����^�C�v�ɂ��A�擾��𕪊򂷂�B<BR>
     * <BR>
     * �@@[�p�����[�^.�����^�C�v == "����"�̏ꍇ]<BR>
     * �@@�@@�g�����������}�l�[�W��.getOrderUnit()���R�[������B<BR>
     * <BR>
     * �@@[�p�����[�^.�����^�C�v == "�敨�E�I�v�V����"�̏ꍇ<BR>
     * �@@�@@OP�����}�l�[�W��.getOrderUnit()���R�[������B<BR>
     * <BR>
     * �@@[�egetOrderUnit()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�����P��ID<BR>
     * <BR>
     * �Q�j�@@�擾���������P�ʂ�ԋp����B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 4325494A02DD
     */
    public OrderUnit getOrderUnit(
        long l_lngOrderUnitId, 
        ProductTypeEnum l_productType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderUnit(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderUnit l_orderUnit = null;

        try
        {
            //�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            //�p�����[�^.�����^�C�v == "�敨�E�I�v�V����"�̏ꍇ 
            else if (ProductTypeEnum.IFO.equals(l_productType))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3OptionOrderManagerImpl l_orderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get�����P��)<BR>
     * �����̒���ID�A���i�敪�ɊY�����钍���P�ʂ�<BR>
     * �擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���i�敪�ɂ��A�擾��𕪊򂷂�B<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("��������" or "�M�p���")�̏ꍇ]<BR>
     * �@@�@@�g�����������}�l�[�W��.getOrderUnits()���R�[������B<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("�敨" or "�I�v�V����")�̏ꍇ<BR>
     * �@@�@@OP�����}�l�[�W��.getOrderUnits()���R�[������B<BR>
     * <BR>
     * �@@[�egetOrderUnit()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.����ID<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��0�Ԗڂ̗v�f��ԋp����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 4326327002F8
     */
    public OrderUnit getOrderUnit(long l_lngOrderId, String l_strCommodityDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrderUnit(long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strCommodityDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderUnit[] l_orderUnits = null;

        //�p�����[�^.���i�敪 == ("��������" or "�M�p���")�̏ꍇ
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) ||
            (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv)))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        }
        //�p�����[�^.���i�敪 == ("�敨" or "�I�v�V����")�̏ꍇ
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) ||
            (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv)))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        }

        if ((l_orderUnits == null) || (l_orderUnits.length < 1))
        {
            log.error("�����P�ʂ��擾���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnits[0];
    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * �����̖����^�C�v�ɊY�����钍���P�ʂ̈ꗗ��<BR>
     * �擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����^�C�v�ɂ�菈���𕪊򂷂�B<BR>
     * �@@[�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ]<BR>
     * �@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@OP�����}�l�[�W��.get�����P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@[�eget�����P�ʈꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�����^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * �@@�@@��������������F�@@�p�����[�^.��������������<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * �@@�@@�\�[�g�����F�@@�p�����[�^.�\�[�g����<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j
     * @@param l_strQueryString - (��������������)<BR>
     * �������� ������
     * @@param l_strQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ��������
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����
     * @@return List
     * @@exception WEB3BaseException
     * @@roseuid 432630790078
     */
    public List getOrderUnitList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderUnitList(WEB3GentradeSubAccount, " +
            "ProductTypeEnum, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || 
            l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lstOrderUnit = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_lstOrderUnit = l_orderManager.getOrderUnits(
                l_subAccount,
                l_productType,
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);
        }
        //��L�ȊO�̏ꍇ
        else
        {
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_lstOrderUnit = l_orderManager.getOrderUnits(
                l_subAccount,
                l_productType,
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lstOrderUnit;
    }
    
    /**
     * (get�A�����������󋵋敪)<BR>
     * �����̖����^�C�v�ɊY�����钍���P�ʂ̈ꗗ��<BR>
     * �擾���A�ԋp����B<BR>
     * <BR>
     * �p�����[�^.�����P�ʂ�� <BR>
     * PR�w�Ŏg�p����A�������̔����󋵋敪��ԋp����B <BR>
     * <BR>
     * �P�j�@@�ҋ@@���̔��� <BR>
     * �@@�@@�@@�����P�ʁD�����L����ԁ�"�I�[�v��"�̏ꍇ�A"�ҋ@@��"��ԋp����B <BR>
     * <BR>
     * �Q�j�@@���������̔��� <BR>
     * �@@�@@�@@�����P��.������ԁ�"3�F�����ρi�V�K�����j"�̏ꍇ�A"��������"��ԋp����B <BR>
     * <BR>
     * �R�j�@@�����R���G���[�̔��� <BR>
     * �@@�@@�@@�����P��.������ԁ�"6�F�������s�i�V�K�����j"�̏ꍇ�A <BR>
     * �@@�@@�@@"�����R���G���["��ԋp����B <BR>
     * <BR>
     * �S�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@return String
     * @@exception WEB3BaseException
     */
    public String getToSuccTriggerOrderStatusType(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getToSuccTriggerOrderStatusType(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTriggerOrderStatus = null;
        
        //�P�j�@@�ҋ@@���̔���
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //�Q�j�@@���������̔���
        else if (OrderStatusEnum.ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }
        //�R�j�@@�����R���G���[�̔���
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR;
        }
        //�S�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B
        else 
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.OTHER;
        }
        
        log.debug("�A�����������󋵋敪 = " + l_strTriggerOrderStatus);
        log.exiting(STR_METHOD_NAME);
        return l_strTriggerOrderStatus;        
    }

}
@
