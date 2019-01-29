head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������Ɖ�T�[�r�X�����N���X(WEB3RuitoOrderReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.define.WEB3OrderStatusDivDef;
import webbroker3.xbruito.message.WEB3RuitoOrderGroup;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoSortKey;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.WEB3RuitoProduct;


/**
 * �ݓ������Ɖ�T�[�r�X�����N���X
 */
public class WEB3RuitoOrderReferenceServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoOrderReferenceService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceServiceImpl.class);

    /**
     * @@roseuid 40A299DF003E
     */

    /**
     * �ݐϓ����̒����Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ��j�����Ɖ�v�Q�ƁB<BR>
     * <BR>
     * 
     * �P)�@@�ݓ������Ɖ�N�G�X�g.validate( )���R�[������O��<BR>
     *      �Ԃ��ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q)�@@�ڋq�ʎ����~�����`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.get�⏕����( )���R�[�����A<BR>
     *             �⏕�����I�u�W�F�N�g���擾����B<BR>
     * �@@�Q�|�Q)�@@�����`�F�b�N.validate����\�ڋq<BR>
     *            (�⏕�����I�u�W�F�N�g : SubAccount)��<BR>
     * �@@           �R�[������O���Ԃ��ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �R)�@@�ݐϓ�����������`�F�b�N<BR>
     * �@@�R�|�P)�@@�ݓ������R���ʃ`�F�b�N.get�C���X�^���X( )��<BR>
     *             �R�[������B<BR>
     * �@@�R�|�Q)�@@�ݓ������R���ʃ`�F�b�N.�ݓ���������J��<BR>
     *            (�⏕�����I�u�W�F�N�g : SubAccount)<BR>
     * �@@�@@�@@�@@  ���R�[������O���Ԃ��ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �S)�@@��t���ԃ`�F�b�N<BR>
     * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�P�Q��)<BR>
     * �@@�S�|�P)�������t�@@���h���t�̎�t���ԃ`�F�b�N<BR>
     * �@@�@@�S�|�P�|�P)�@@ThreadLocalSystemAttributesRegistry��<BR>
     *                 ����J�����_�R���e�L�X�g�̓��e��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ȉ��̂悤�ɕύX����B<BR>
     * �@@�@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *             WEB3TradingTimeTypeDef.����F<BR>
     * �@@�@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =<BR>
     *              "01:���t(�V�K����)"<BR>
     
     * �@@�@@�S�|�P�|�Q)�@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �@@�@@�S�|�P�|�R)�@@������ԊǗ�.validate������t�\( )��<BR>
     *                      �R�[������B<BR>
     * �@@�@@�S�|�P�|�R�|�P)�@@validate������t�\�����O��<BR>
     *           �Ԃ���Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@is����F���t��t�\(�����G���A)��"true"���Z�b�g�B<BR>
     * �@@�@@�S�|�P�|�R�|�Q)�@@validate������t�\�����O��<BR>
     *          �Ԃ��ꂽ�ꍇ<BR>
     * �@@�@@�@@�@@is����F���t��t�\(�����G���A)��"false"���Z�b�g�B<BR>
     * <BR>
     
     
     * �@@�S�|�Q)�������t�@@���h���̎�t���ԃ`�F�b�N<BR>
     * �@@�@@�S�|�Q�|�P)�@@ThreadLocalSystemAttributesRegistry��<BR            
     * ����J�����_�R���e�L�X�g�̓��e��<BR>
     * �@@�@@   �ȉ��̂悤�ɕύX����B<BR>
     * �@@�@@ �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *               WEB3TradingTimeTypeDef.����F<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *              "02:���t(�V�K����)"<BR>
     * �@@�@@�S�|�Q�|�Q)�@@������ԊǗ�.setTimestamp()��<BR>
     *         �R�[������B<BR>
     * <BR>
     * �@@�@@�S�|�Q�|�R)�@@������ԊǗ�.validate������t�\( )��<BR>
     *         �R�[������B<BR>
     * �@@�@@�S�|�Q�|�R�|�P)�@@validate������t�\�����O��<BR>
     *          �Ԃ���Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@ is����F����t�\(�����G���A)��"true"���Z�b�g�B<BR>
     * �@@�@@�S�|�Q�|�R�|�Q)�@@validate������t�\�����O��<BR>
     *          �Ԃ��ꂽ�ꍇ<BR>
     * �@@�@@�@@  is����F����t�\(�����G���A)��"false"���Z�b�g�B<BR>
     * <BR>
     * �@@�S�|�R)�@@MMF���t�̎�t���ԃ`�F�b�N<BR>
     * �@@�@@�S�|�R�|�P)�@@ThreadLocalSystemAttributesRegistry��<BR>
     *       ����J�����_�R���e�L�X�g�̓��e���ȉ��̂悤�ɕύX����B<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *             WEB3TradingTimeTypeDef.MMF(�ݒ�)<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *               "01:���t(�V�K����)"<BR>
     * �@@�@@�S�|�R�|�Q)�@@������ԊǗ�.setTimestamp()��<BR>
     *        �R�[������B<BR>
     * <BR>
     * �@@�@@�S�|�R�|�R)�@@������ԊǗ�.validate������t�\( )��<BR>
     *         �R�[������B<BR>
     * �@@�@@�S�|�R�|�R�|�P)�@@validate������t�\�����O��<BR>
     *           �Ԃ���Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@isMMF���t��t�\(�����G���A)��"true"���Z�b�g�B<BR>
     * �@@�@@�S�|�R�|�R�|�Q)�@@validate������t�\�����O��<BR>
     *           �Ԃ��ꂽ�ꍇ<BR>
     * �@@�@@�@@�@@ isMMF���t��t�\(�����G���A)��"false"���Z�b�g�B<BR>
     * <BR>
     * �@@�S�|�S)�@@MMF���̎�t���ԃ`�F�b�N<BR>
     * �@@�@@�S�|�S�|�P)�@@ThreadLocalSystemAttributesRegistry��<BR>
     *           ����J�����_�R���e�L�X�g�̓��e��<BR>
     * �@@�@@�@@�@@�ȉ��̂悤�ɕύX����B<BR>
     * �@@�@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *               WEB3TradingTimeTypeDef.MMF(�ݒ���)<BR>
     * �@@�@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *               "02:���t(�V�K����)"<BR>
     * �@@�@@�S�|�R�|�Q)�@@������ԊǗ�.setTimestamp()��<BR>
     *           �R�[������B<BR>
     * <BR>
     * �@@�@@�S�|�S�|�R)�@@������ԊǗ�.validate������t�\( )��<BR>
     *           �R�[������B<BR>
     * �@@�@@�S�|�S�|�R�|�P)�@@validate������t�\�����O��<BR>
     *           �Ԃ���Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@isMMF����t�\(�����G���A)��"true"���Z�b�g�B<BR>
     * �@@�@@�S�|�S�|�R�|�Q)�@@validate������t�\�����O��<BR>
     *          �Ԃ��ꂽ�ꍇ<BR>
     * �@@�@@�@@�@@isMMF����t�\(�����G���A)��"false"���Z�b�g�B<BR>
     * <BR>
     * �@@�S�|�T)�@@���N�G�X�g�f�[�^.�Ɖ�敪=="1:����ꗗ"�̏ꍇ<BR>
     * �@@�@@�@@�@@�ȉ��̏������������ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@��is����F���t��t�\==false and <BR>
     *             is����F����t�\==false and <BR>
     * �@@�@@�@@�@@�@@ isMMF���t��t�\==false and�@@<BR>
     *             isMMF����t�\==false  <BR>
     * �@@�@@�@@�@@(����F���t�A����F���AMMF�ݒ�A<BR>
     *           MMF�ݒ��񂪎�t�o���Ȃ����)<BR>
     *          class    : WEB3BusinessLayerException<BR>
     *          tag      : BUSINESS_ERROR_00199<BR>
     * �T)�@@create�\�[�g����(���N�G�X�g�f�[�^.�\�[�g�L�[ : <BR>
     *      �ݓ��\�[�g�L�[)���R�[�����A<BR>
     * �@@   ���N�G�X�g�f�[�^�ɂēn���ꂽ�\�[�g�������擾����B<BR>
     * <BR>
     * �U)�@@���������ɍ��v����S�Ă̗ݓ������P�ʃI�u�W�F�N�g��<BR>
     *      �擾����B<BR>
     * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�Q�Q��)<BR>
     * �@@�@@�g���ݓ������}�l�[�W��.get�ݓ������P�ʈꗗ���R�[�����A<BR>
     *     ���������ɍ��v����<BR>
     * �@@�@@�@@�@@�S�Ă̗ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[get�ݓ������P�ʈꗗ�̈���]<BR>
     * �@@�@@�@@�@@�⏕�����I�u�W�F�N�g : �⏕����<BR>
     * �@@�@@�@@�@@create�\�[�g�������\�b�h�߂�l : String)<BR>
     * <BR>
     * �V)�@@�U)��get�ݓ������P�ʈꗗ���\�b�h�߂�l�̑S�Ăɑ΂��āA<BR>
     *      �������ꂼ��Ɏ�����\��<BR>
     * �@@�@@�ǂ����̔�����s���A���̔��茋�ʂ����ƂɁA<BR>
     *     �Ɖ�敪�ɊY�����郌�X�|���X���׃f�[�^<BR>
     * �@@�@@(�ݓ������Ɖ���P�ʃN���X)�𐶐�����B<BR>
     * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�R�Q��)<BR>
     * <BR>
     * �@@�V�|�P�|�P)�@@this.validate����ۃ��\�b�h���R�[�����A<BR>
     *          ����������\�Ȓ������ǂ����𔻒肷��B<BR>
     * �@@�@@�@@�@@[validate����ۂ̈���]<BR>
     * �@@�@@�@@�@@�@@�ݓ������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@is����F���t��t�\<BR>
     * �@@�@@�@@�@@�@@is����F����t�\<BR>
     * �@@�@@�@@�@@�@@isMMF���t��t�\<BR>
     * �@@�@@�@@�@@�@@isMMF����t�\<BR>
     * <BR>
     * �@@�V�|�P�|�Q)�@@�����Ɖ�̏ꍇ�͌������ʂ̑S�āA<BR>
     *          ����ꗗ�̏ꍇ�͎���\�Ȓ����݂̂�<BR>
     * �@@�@@�@@�@@�ΏۂɁA���X�|���X���׃f�[�^���쐬����B<BR>
     * <BR>
     * �@@�@@�@@�����N�G�X�g�f�[�^.�Ɖ�敪=="0:�����Ɖ�"�̏ꍇ�A�܂���<BR>
     * �@@�@@�@@�����N�G�X�g�f�[�^.�Ɖ�敪=="1:����ꗗ"�ł���A<BR>
     * �@@�@@�@@�@@�V�|�P�|�Q)��this.validate����ۃ��\�b�h�߂�l��<BR>
     *          "true"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�V�|�P�|�R�|�P)�@@���X�|���X���׃f�[�^�̃C���X�^���X��<BR>
     *          ��������B<BR>
     * <BR>
     * �@@�@@�V�|�P�|�R�|�Q)�@@�����敪�̎擾<BR>
     * �@@�@@�@@�@@�@@�|�ݓ������P��Params.getOrderType()���R�[�����A<BR>
     *               ������ʂ��擾����B<BR>
     * �@@�@@�@@�@@�@@�|�ݓ������P��.�������<BR>
     *                               ==OrderTypeEnum.RUITO_BUY<BR>
     *               �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@"1:���t"���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�|������� == OrderTypeEnum.RUITO_SELL�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݓ������P��Params.getGpSellDiv()���R�[�����A<BR>
     *                 �ݓ����敪���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�V�|�P�|�R�|�R)�@@���X�|���X���׃f�[�^�Ɉȉ��̂悤�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.ID=�ݓ������P��.����ID<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.�����R�[�h=�ݓ������P��.getProduct( )<BR>
     *          �̖߂�l.�����R�[�h<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.������=�ݓ������P��.getProduct( )<BR>
     *           �̖߂�l.������<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.�����敪(�ݓ�)=�V�|�P�|�R�|�R)��<BR>
     *           �擾���������敪<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.��������=�ݓ������P��.�󒍓���<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.�������ʋ敪=(*)<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.��������=�ݓ������P��.��������<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.������ԋ敪=<BR>
     * �@@�@@�@@ �@@�@@�@@this.get������ԋ敪(�ݓ������P��)���\�b�h�߂�l<BR>
     * �@@�@@�@@�@@���X�|���X���׃f�[�^.����\�t���O=<BR>
     * �@@�@@�@@�@@�@@�@@�V�|�P�|�Q)��this.validate�����( )���\�b�h�߂�l<BR>
     *              ���Z�b�g<BR>
     * �@@ (*1)���X�|���X���׃f�[�^.�����`���l��=<BR>
     *                  �ݓ������P��.���񒍕��̒����`���l��<BR>
     * �@@ (*1)���X�|���X���׃f�[�^.�����o�H�敪=<BR>
     *                  �ݓ������P��.�����o�H�敪<BR>
     * �@@ (*1)���X�|���X���׃f�[�^.�I�y���[�^�R�[�h=<BR>
     *                  �ݓ������P��.�����ID��null�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader(�ݓ�����<BR>
     *                  �P��.�����ID).���҃R�[�h���Z�b�g�B<BR>
     * <BR>
     *   *)�@@�ݓ������P��.�������ʃ^�C�v=<BR>
     *             "1:����"�̏ꍇ�A�������ʋ敪��"4:����"���Z�b�g<BR>
     * �@@�@@   �ݓ������P��.�������ʃ^�C�v=<BR>
     *            "2:���z"�̏ꍇ�A�������ʋ敪��"3:���z"���Z�b�g<BR>
     * (*1) <BR>
     *  this.get�㗝���͎�()��null �̏ꍇ�̂݃Z�b�g���s���B <BR>
     * <BR>
     * �W)�@@�V)�ō쐬�������X�|���X���׃f�[�^�̈ꗗ�����ɁA�ȉ���ݒ肷��B<BR>
     * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�S�Q��)<BR>
     * <BR>
     * �@@�W�|�P)�@@���N�G�X�g�f�[�^.createResponse( )���\�b�h���R�[�����A<BR>
     * �@@�@@�@@�@@���X�|���X�f�[�^(�ݓ������Ɖ�X�|���X�N���X)�𐶐�����B<BR>
     * <BR>
     * �@@�W�|�Q)�@@���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B<BR>
     * �@@�@@�@@���X�|���X.���y�[�W��:�@@�V)�Ŋm�肵�����ׂ̗v�f����<BR>
     *        ���N�G�X�g.�y�[�W���\���s��<BR>
     * �@@�@@�@@�@@�@@���]�肪�o��ꍇ�́A�{�P�����l��ݒ�B<BR>
     * �@@�@@�@@�@@�@@���V)�Ŋm�肵�����ׂ̗v�f����0<BR>
     *               (�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A 0���Z�b�g�B<BR>
     *               ���X�|���X.�����R�[�h��:�@@�V)�Ŋm�肵�����ׂ̗v�f��<BR>
     * �@@�@@�@@�@@�@@�@@���X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩):<BR>
     * �@@�@@�@@�@@�@@�@@�V)�Ŋm�肵�����ׂ̗v�f�� > <BR>
     *             (���N�G�X�g.�y�[�W���\���s���~<BR>
     *                      (���N�G�X�g.�v���y�[�W�ԍ�-1) )<BR>
     * �@@�@@�@@�@@�@@�@@�ł���΁A���N�G�X�g.�v���y�[�W�ԍ��B<BR>
     * �@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ�́A���X�|���X.���y�[�W�� �����̂܂ܐݒ�B<BR>
     * �@@�@@�@@�@@�@@���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A<BR>
     *               �ŏI�y�[�W��<BR>
     * �@@�@@�@@�@@�@@�Y������f�[�^�����X�|���X�ɐݒ肷��B<BR>
     * <BR>
     * �@@�W�|�R)�@@�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A<BR>
     *              ���X�|���X.�������ꗗ<BR>
     * �@@�@@�@@(�ݓ������Ɖ���P��[ ])��null���Z�b�g����O���X���[����B<BR>
     *          class    : WEB3BusinessLayerException<BR>
     *          tag      : BUSINESS_ERROR_00200<BR>
     * <BR>
     * �X)�@@�W)�Ŋm�肵�����ׂ̂����A���X�|���X�f�[�^�ɐݒ肷�閾�ׂ����߂�B<BR>
     * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�T�Q��)<BR>
     * <BR>
     * �@@�X�|�P)�@@(���N�G�X�g.�y�[�W���\���s���~<BR>
     *                      (���X�|���X.�\���y�[�W�ԍ��|1)�����A<BR>
     * �@@�@@�@@�@@�W)�Ŋm�肵�����X�|���X���׃f�[�^�ꗗ��<BR>
     * �@@�@@�@@�@@�v�f���X�L�b�v����B<BR>
     * <BR>
     * �@@�X�|�Q)�@@��L�Ō��肵�����X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��`<BR>
     * �@@�@@�@@�@@(���X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��{<BR>
     *                                 ���N�G�X�g.�y�[�W���\���s��)<BR>
     * �@@�@@�@@�@@�܂łɊY�����郌�X�|���X���׃f�[�^���A<BR>
     *          ���X�|���X�f�[�^.�������ꗗ<BR>
     * �@@�@@�@@�@@�Ƃ��ăZ�b�g����B<BR>
     * <BR>
     * �@@�X�|�R)�@@���X�|���X�����^�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405A4BD20127
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }        
        
        WEB3RuitoOrderReferenceRequest l_orderReferRequest =
            (WEB3RuitoOrderReferenceRequest) l_request;
        l_orderReferRequest.validate();
        log.debug("�ݓ������Ɖ�N�G�X�g.validate( )���R�[����");
        
        //=====================================================================
        // �⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //�����`�F�b�N.validate����\�ڋq
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   

        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }       
     
        // �ݓ������R���ʃ`�F�b�N�̃C���X�^���X���擾����B
        WEB3RuitoOrderManagerReusableValidationsCheck l_orderMgrResVal =
            (WEB3RuitoOrderManagerReusableValidationsCheck) 
            WEB3RuitoOrderManagerReusableValidationsCheck.getInstance();
        
        //�ݐϓ�����������`�F�b�N 
        try
        {
            l_orderMgrResVal.validateRuitoTradedAccountEstablish(l_subAccount);
            log.debug("�ݐϓ�����������`�F�b�N");
        }
        catch(OrderValidationException e)
        {
            log.error("�ݐϓ�����������`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ݐϓ�����������`�F�b�N�G���[");
        }            
        //is����F���t��t�\(�����G���A)
        boolean l_blnMidFBuyAccept = false;
        //is����F����t�\(�����G���A)
        boolean l_blnMidFSellAccept = false;
        //isMMF���t��t�\(�����G���A)
        boolean l_blnMMFBuy = false;
        //isMMF����t�\(�����G���A)
        boolean l_blnMMFSell = false;
        
        //ThreadLocalSystemAttributesRegistry���A
        //����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) 
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        try
        {
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h
            //WEB3TradingTimeTypeDef.����F�h
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //�h01:���t(�V�K����)�h
            l_context.setOrderAcceptTransaction(
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
            WEB3GentradeTradingTimeManagement.setTimestamp();    
            WEB3GentradeTradingTimeManagement.validateOrderAccept();          
            l_blnMidFBuyAccept = true;
            log.debug("l_blnMidFBuyAccept = true");
        }

        catch(WEB3BaseException l_ex)
        {
            l_blnMidFBuyAccept = false;
            log.debug("l_blnMidFBuyAccept = false");
        }
        try
        {
            //�S�|�Q)�������t�@@���h���̎�t���ԃ`�F�b�N
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �hWEB3TradingTimeTypeDef.����F�h
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02:���t(�V�K����)�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);

            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            l_blnMidFSellAccept = true;
            log.debug("l_blnMidFSellAccept = true");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMidFSellAccept = false;
            log.debug("l_blnMidFSellAccept = false");
        }
        
        //�S�|�R)�@@MMF���t�̎�t���ԃ`�F�b�N
        try
        {            
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �hWEB3TradingTimeTypeDef.MMF(�ݒ�)�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h01:���t(�V�K����)�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);                
            WEB3GentradeTradingTimeManagement.setTimestamp();
    
            l_blnMMFBuy = true;
    
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("l_blnMMFBuy = true");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMMFBuy = false;
            log.debug("l_blnMMFBuy = false");
        }
        
        //�S�|�S)�@@MMF���̎�t���ԃ`�F�b�N<BR>     
        try
        {            
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = WEB3TradingTimeTypeDef.MMF(�ݒ���)
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET_CANCEL);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02:���t(�V�K����)�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);              
    
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            l_blnMMFSell = true;
            log.debug("l_blnMMFSell = true;");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMMFSell = false;
            log.debug("l_blnMMFSell = false");
        }      

        //�S�|�T)�@@���N�G�X�g�f�[�^.�Ɖ�敪=="1:����ꗗ"�̏ꍇ�ȉ���
        //�������������ꂽ�ꍇ�A��O���X���[����B
        if (WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType))
        {
            if (l_blnMidFBuyAccept == false && l_blnMidFSellAccept == false &&
                l_blnMMFBuy == false && l_blnMMFSell == false)
            {
                log.debug("__���N�G�X�g�f�[�^.�Ɖ�敪==1:����ꗗ�̏ꍇ__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00199, STR_METHOD_NAME,  
                    "���N�G�X�g�f�[�^.�Ɖ�敪=='1:����ꗗ'�̏ꍇ�ȉ��̏������������ꂽ�ꍇ");
            }
        }

        //�T)�@@create�\�[�g����
        WEB3RuitoSortKey l_sortKey[] = l_orderReferRequest.ruitoSortKeys;

        String l_strSort = createSortCondType(l_sortKey);

        /* �U)�@@���������ɍ��v����S�Ă̗ݓ������P�ʃI�u�W�F�N�g��<BR>
         *      �擾����B<BR>
         * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�Q�Q��)<BR>
         * �@@�@@�g���ݓ������}�l�[�W��.get�ݓ������P�ʈꗗ���R�[�����A<BR>
         *     ���������ɍ��v����<BR>
         * �@@�@@�@@�@@�S�Ă̗ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
         * �@@�@@[get�ݓ������P�ʈꗗ�̈���]<BR>
         * �@@�@@�@@�@@�⏕�����I�u�W�F�N�g : �⏕����<BR>
         * �@@�@@�@@�@@create�\�[�g�������\�b�h�߂�l : String)<BR>
         * <BR>
         * �V)�@@�U)��get�ݓ������P�ʈꗗ���\�b�h�߂�l�̑S�Ăɑ΂��āA<BR>
         *      �������ꂼ��Ɏ�����\��<BR>
         * �@@�@@�ǂ����̔�����s���A���̔��茋�ʂ����ƂɁA<BR>
         *     �Ɖ�敪�ɊY�����郌�X�|���X���׃f�[�^<BR>
         * �@@�@@(�ݓ������Ɖ���P�ʃN���X)�𐶐�����B<BR>
         * ��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�R�Q��)<BR>*/
     
        //�U)�@@���������ɍ��v����S�Ă̗ݓ������P�ʃI�u�W�F�N�g���擾����B
        WEB3RuitoOrderManager l_ruitoOrderManager =
           (WEB3RuitoOrderManager) l_finApp
               .getTradingModule(ProductTypeEnum.RUITO)
               .getOrderManager();   
        //******need add��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�Q�Q��)
        List l_lisRuitoOrderUnit = new Vector();
        try
        {        
            l_lisRuitoOrderUnit =
                    l_ruitoOrderManager.getOrderUnits(l_subAccount, l_strSort);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�V)//******need add��(�V�[�P���X�}�u(�ݓ�)�����Ɖ�v-�R�Q��)        
        
        /* �@@�V�|�P�|�P)�@@this.validate����ۃ��\�b�h���R�[�����A<BR>
        *          ����������\�Ȓ������ǂ����𔻒肷��B<BR>
        * �@@�@@�@@�@@[validate����ۂ̈���]<BR>
        * �@@�@@�@@�@@�@@�ݓ������P�ʃI�u�W�F�N�g<BR>
        * �@@�@@�@@�@@�@@is����F���t��t�\<BR>
        * �@@�@@�@@�@@�@@is����F����t�\<BR>
        * �@@�@@�@@�@@�@@isMMF���t��t�\<BR>
        * �@@�@@�@@�@@�@@isMMF����t�\<BR>
        * <BR>
        * �@@�V�|�P�|�Q)�@@�����Ɖ�̏ꍇ�͌������ʂ̑S�āA<BR>
        *          ����ꗗ�̏ꍇ�͎���\�Ȓ����݂̂�<BR>
        * �@@�@@�@@�@@�ΏۂɁA���X�|���X���׃f�[�^���쐬����B<BR>
        * <BR>
        * �@@�@@�@@�����N�G�X�g�f�[�^.�Ɖ�敪=="0:�����Ɖ�"�̏ꍇ�A�܂���<BR>
        * �@@�@@�@@�����N�G�X�g�f�[�^.�Ɖ�敪=="1:����ꗗ"�ł���A<BR>
        * �@@�@@�@@�@@�V�|�P�|1)��this.validate����ۃ��\�b�h�߂�l��<BR>
        *          "true"�̏ꍇ<BR>
        * <BR>
        * �@@�@@�V�|�P�|�R�|�P)�@@���X�|���X���׃f�[�^�̃C���X�^���X��<BR>
        *          ��������B<BR>*/
        //�V�|�P�|�P)
        RuitoOrderUnit l_ruitoOrderUnit = null;
        RuitoOrderUnitRow l_ruitoOrderUnitRow = null;

        List l_lisRuitoOrderGroups = new Vector();

        for (int i = 0; i < l_lisRuitoOrderUnit.size(); i++)
        {
            l_ruitoOrderUnit = (RuitoOrderUnit) l_lisRuitoOrderUnit.get(i);
            l_ruitoOrderUnitRow = 
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();   
               
            boolean l_blnValidResult = this.validateCancelAbleUnable(
                l_ruitoOrderUnit,l_blnMidFBuyAccept,
                l_blnMidFSellAccept,l_blnMMFBuy,l_blnMMFSell);
            log.debug("l_blnValidResult="+new Boolean(
                l_blnValidResult).toString());        
            //�V�|�P�|�Q)
            
            if ( (!WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType))|| 
                    ((WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType) && l_blnValidResult)))
            { 
                 log.debug("���X�|���X���׃f�[�^�ݒ�");
                /* �@@�@@�V�|�P�|�R�|�Q)�@@�����敪�̎擾<BR>
                 * �@@�@@�@@�@@�@@�|�ݓ������P��Params.getOrderType()���R�[�����A<BR>
                 *               ������ʂ��擾����B<BR>
                 * �@@�@@�@@�@@�@@�|�ݓ������P��.�������<BR>
                 *                               ==OrderTypeEnum.RUITO_BUY<BR>
                 *               �̏ꍇ<BR>
                 * �@@�@@�@@�@@�@@�@@�@@"1:���t"���Z�b�g����B<BR>
                 * �@@�@@�@@�@@�@@�|������� == OrderTypeEnum.RUITO_SELL�̏ꍇ<BR>
                 * �@@�@@�@@�@@�@@�@@�@@�ݓ������P��Params.getGpSellDiv()���R�[�����A<BR>
                 *                 �ݓ����敪���Z�b�g����B<BR>*/
                 
                //�V�|�P�|�R�|�Q)�@@�����敪�̎擾
                RuitoOrderUnitParams l_params = 
                    (RuitoOrderUnitParams) 
                        l_ruitoOrderUnit.getDataSourceObject();
                String l_strTradedDiv = "";
                OrderTypeEnum l_orderType = l_params.getOrderType();
                if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
                {
                    l_strTradedDiv = WEB3BuySellTypeDef.BUY;
                    log.debug("l_orderType="+l_orderType.stringValue());
                    log.debug("l_strTradedDiv="+l_strTradedDiv);
                    
                }
                if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
                {
                    l_strTradedDiv = l_params.getGpSellDiv();
                    log.debug("l_orderType="+l_orderType.stringValue());
                    log.debug("l_strTradedDiv="+l_strTradedDiv);
                }
        
                //���X�|���X���׃f�[�^�Ɉȉ��̂悤�ɒl���Z�b�g����B
                //���X�|���X���׃f�[�^.ID=�ݓ������P��.����ID
            
                /*)�@@�ݓ������P��.�������ʃ^�C�v="1:����"�̏ꍇ�A
                     �������ʋ敪��"4:����"���Z�b�g 
        �@@�@@         �ݓ������P��.�������ʃ^�C�v="2:���z"�̏ꍇ�A
                     �������ʋ敪��"3:���z"���Z�b�g */
                String l_strQuantityType = "";
                if (QuantityTypeEnum.QUANTITY.equals(
                     l_ruitoOrderUnit.getQuantityType()))
                {
                    l_strQuantityType = "4";
                    log.debug("l_strQuantityType =" +l_strQuantityType);
                }     
                if (QuantityTypeEnum.AMOUNT.equals(
                     l_ruitoOrderUnit.getQuantityType()))
                {
                    l_strQuantityType = "3";
                    log.debug("l_strQuantityType =" +l_strQuantityType);
                }              
                
         
                WEB3RuitoProduct l_ruitoProduct = 
                    (WEB3RuitoProduct) l_ruitoOrderUnit.getProduct();
                WEB3RuitoOrderGroup l_ruitoOrderGroup = new WEB3RuitoOrderGroup();
                //���X�|���X���׃f�[�^.ID = �ݓ������P��.����ID
                l_ruitoOrderGroup.id = l_ruitoOrderUnit.getOrderId() + "";
                log.debug("���X�|���X���׃f�[�^.ID=" + l_ruitoOrderGroup.id);
    
                //���X�|���X���׃f�[�^.�����R�[�h=
                //�ݓ������P��.getProduct( )�̖߂�l.�����R�[�h
                l_ruitoOrderGroup.ruitoProductCode = 
                    l_ruitoProduct.getProductCode();
                log.debug("���X�|���X���׃f�[�^.�����R�[�h="+
                        l_ruitoOrderGroup.ruitoProductCode);
    
                //���X�|���X���׃f�[�^.������=
                //�ݓ������P��.getProduct( )�̖߂�l.������
                l_ruitoOrderGroup.ruitoProductName = 
                    l_ruitoProduct.getProductName();
                log.debug("���X�|���X���׃f�[�^.������="+
                        l_ruitoOrderGroup.ruitoProductName);
                    
                //���X�|���X���׃f�[�^.�����敪(�ݓ�)=
                //�V�|�P�|�R�|�R)�Ŏ擾���������敪
                l_ruitoOrderGroup.ruitoDealingType = l_strTradedDiv;
                log.debug("���X�|���X���׃f�[�^.�����敪(�ݓ�)="+
                        l_ruitoOrderGroup.ruitoDealingType);
    
                //���X�|���X���׃f�[�^.��������=�ݓ������P��.�󒍓���
                l_ruitoOrderGroup.orderDate = 
                    l_ruitoOrderUnitRow.getReceivedDateTime();
                log.debug("���X�|���X���׃f�[�^.�������� = "+
                        l_ruitoOrderGroup.orderDate);
                    
                //���X�|���X���׃f�[�^.�������ʋ敪=(*)
                l_ruitoOrderGroup.ruitoOrderQuantityType = l_strQuantityType;
                log.debug("���X�|���X���׃f�[�^.�������ʋ敪 = "+
                        l_ruitoOrderGroup.ruitoOrderQuantityType);
    
                //���X�|���X���׃f�[�^.��������=�ݓ������P��.��������
                l_ruitoOrderGroup.ruitoOrderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_ruitoOrderUnit.getQuantity());
                log.debug("���X�|���X���׃f�[�^.�������� = "+
                        l_ruitoOrderGroup.ruitoOrderQuantity);                
                 
                //���X�|���X���׃f�[�^.������ԋ敪= 
                //this.get������ԋ敪(�ݓ������P��)���\�b�h�߂�l
                l_ruitoOrderGroup.orderState = 
                    this.getOrderStatusType(l_ruitoOrderUnit);
                log.debug("���X�|���X���׃f�[�^.������ԋ敪="+
                        l_ruitoOrderGroup.orderState);    
    
                //���X�|���X���׃f�[�^.����\�t���O= �V�|�P�|�Q)
                //��this.validate�����( )���\�b�h�߂�l
                l_ruitoOrderGroup.cancelFlag = l_blnValidResult;
                log.debug("���X�|���X���׃f�[�^.����\�t���O="+
                        l_ruitoOrderGroup.cancelFlag);
                
                //this.get�㗝���͎�()��null �̏ꍇ�̂݃Z�b�g���s���B 
                if (this.getTrader() != null)
                {
                    log.debug("this.get�㗝���͎�()��null �̏ꍇ");
                    //���X�|���X���׃f�[�^.�����`���l�� = 
                    //�ݓ������P��.���񒍕��̒����`���l��<BR>
                    l_ruitoOrderGroup.orderChannel = l_ruitoOrderUnitRow.getOrderChanel();
                    log.debug("���X�|���X���׃f�[�^.�����`���l��="+
                            l_ruitoOrderGroup.orderChannel);
        
                    //���X�|���X���׃f�[�^.�����o�H�敪=�ݓ������P��.�����o�H�敪<BR>
                    l_ruitoOrderGroup.orderRootDiv = l_ruitoOrderUnitRow.getOrderRootDiv();
                    log.debug("���X�|���X���׃f�[�^.�����o�H�敪="+
                            l_ruitoOrderGroup.orderRootDiv);
                    
                    //���X�|���X���׃f�[�^.�I�y���[�^�R�[�h
                    //=�ݓ������P��.�����ID��null�̏ꍇ�̂݁A 
                    //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader
                    //(�ݓ������P��.�����ID).���҃R�[�h���Z�b�g�B
                    if(l_ruitoOrderUnit.getTraderId() > 0)
                    {
                        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrade()        
                        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    
                        Trader l_trader = null;
                        try
                        {
                            l_trader = l_gentradeFinObjectManager.getTrader(
                                    l_ruitoOrderUnit.getTraderId());
                  
                        }
                        catch (NotFoundException l_ex)
                        {           
                            log.error("__NotFoundException__", l_ex);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                "searchExecuteReference");
                        }        
                        l_ruitoOrderGroup.operatorCode = 
                            new Long(l_trader.getTraderId()).toString();
                        log.debug("���X�|���X���׃f�[�^.�I�y���[�^�R�[�h="+
                                l_ruitoOrderGroup.operatorCode); 
                    }
                }
                l_lisRuitoOrderGroups.add(l_ruitoOrderGroup);
            }

        }
        WEB3RuitoOrderGroup[] l_ruitoOrderGroups = new WEB3RuitoOrderGroup[l_lisRuitoOrderGroups.size()];
        l_lisRuitoOrderGroups.toArray(l_ruitoOrderGroups);
        
        WEB3RuitoOrderReferenceResponse l_ruitoOrderReferenceResponse =
            (WEB3RuitoOrderReferenceResponse) l_orderReferRequest.createResponse();
        
        //�W�|�Q)�@@���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B 
        // --------------Start-------------------- TotalPages
        int l_intTotalPages =
            l_ruitoOrderGroups.length / Integer.parseInt(l_orderReferRequest.pageSize);
        
        if (l_ruitoOrderGroups.length == 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = "0";
        }
        else if (l_ruitoOrderGroups.length % Integer.parseInt(l_orderReferRequest.pageSize) == 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages);
        }
        else if (l_ruitoOrderGroups.length % Integer.parseInt(l_orderReferRequest.pageSize) > 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages + 1);
        }
        log.debug("totalPages=" + l_ruitoOrderReferenceResponse.totalPages);
        //--------------- End --------------- TotalPages

        // --------------- Start ---------------- TotalRecords
        //���X�|���X.�����R�[�h��:        
        //���X�|���X.�����R�[�h��:�@@�V)�Ŋm�肵�����ׂ̗v�f�� 
        l_ruitoOrderReferenceResponse.totalRecords = 
            WEB3StringTypeUtility.formatNumber(l_ruitoOrderGroups.length);

        log.debug("totalRecords="+l_ruitoOrderReferenceResponse.totalRecords);
        // -------------- End -------------------TotalRecords
        
        /*���X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
�@@�@@�@@�@@�@@   �V)�Ŋm�肵�����ׂ̗v�f�� > (���N�G�X�g.�y�[�W���\���s���~
        //   (���N�G�X�g.�v���y�[�W�ԍ�-1) ) 
�@@�@@�@@�@@�@@�@@  �ł���΁A���N�G�X�g.�v���y�[�W�ԍ��B 
�@@�@@�@@�@@�@@�@@  ��L�ȊO�̏ꍇ�́A���X�|���X.���y�[�W�� �����̂܂ܐݒ�B*/ 
        // ------------- Start ---------------- PageIndex
        if (l_ruitoOrderGroups.length > 
            Integer.parseInt(l_orderReferRequest.pageSize) * (Integer.parseInt(l_orderReferRequest.pageIndex) - 1))
        {
            l_ruitoOrderReferenceResponse.pageIndex = l_orderReferRequest.pageIndex;
        }
        else
        {
            l_ruitoOrderReferenceResponse.pageIndex = l_ruitoOrderReferenceResponse.totalPages;
        }
        // ------------ End ------------------- PageIndex
        if ("0".equals(l_ruitoOrderReferenceResponse.totalPages))
        {
            l_ruitoOrderReferenceResponse.ruitoOrderGroups = null;
            log.debug("���M�����Ɖ�X�|���X.���y�[�W�� = 0");
			return l_ruitoOrderReferenceResponse;
        }
        /* �@@�X�|�P)�@@(���N�G�X�g.�y�[�W���\���s���~<BR>
             *                      (���X�|���X.�\���y�[�W�ԍ��|1)�����A<BR>
             * �@@�@@�@@�@@�W)�Ŋm�肵�����X�|���X���׃f�[�^�ꗗ��<BR>
             * �@@�@@�@@�@@�v�f���X�L�b�v����B<BR>
             * <BR>
       */
        // ------------ Start ---------------- RecordBegin & RecordEnd
        int l_intRecordBegin =
            Integer.parseInt(l_orderReferRequest.pageSize)
                * (Integer.parseInt(l_ruitoOrderReferenceResponse.pageIndex) - 1);
        int l_intRecordEnd = 0;
        if (l_ruitoOrderReferenceResponse.pageIndex.equals(l_ruitoOrderReferenceResponse.totalPages))
        {
            l_intRecordEnd = l_ruitoOrderGroups.length;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_orderReferRequest.pageSize);
        }
        // ------------ End ------------- RecordBegin & RecordEnd
           
       /* 
             * �@@�X�|�Q)�@@��L�Ō��肵�����X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��`<BR>
             * �@@�@@�@@�@@(���X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��{<BR>
             *                                 ���N�G�X�g.�y�[�W���\���s��)<BR>
             * �@@�@@�@@�@@�܂łɊY�����郌�X�|���X���׃f�[�^���A<BR>
             *          ���X�|���X�f�[�^.�������ꗗ<BR>
             * �@@�@@�@@�@@�Ƃ��ăZ�b�g����B<BR>
        */
        // -----------------Start-------------- ReturnValue RuitoOrderGroups
        List l_lisReturnRuitoOrderGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            l_lisReturnRuitoOrderGroups.add(l_ruitoOrderGroups[i]);
        }
        WEB3RuitoOrderGroup[] l_returnRuitoOrderGroups = 
            new WEB3RuitoOrderGroup[l_lisReturnRuitoOrderGroups.size()];
        l_lisReturnRuitoOrderGroups.toArray(l_returnRuitoOrderGroups);
        // -----------------End --------------- ReturnValue RuitoOrderGroups

        l_ruitoOrderReferenceResponse.ruitoOrderGroups = l_returnRuitoOrderGroups;
        log.exiting(STR_METHOD_NAME);    
        return l_ruitoOrderReferenceResponse;
    }

    /**
     * (create�\�[�g����)<BR>
     * <BR>
     * ����:�\�[�g�L�[���AQueryProcessor�̃p�����[�^�Ƃ��ėp����<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �\�[�g������������쐬���Ԃ��B<BR>
     * <BR>
     * �P)�@@�����̃\�[�g�L�[.�L�[���ڂ̐����A<BR>
     *       �Ή�����e�[�u���̗񕨗�����<BR>
     * �@@�@@�@@�����^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����@@�@@�@@�@@�@@:�ݓ������P�ʃe�[�u��.����ID<BR>
     * �@@�@@�@@�@@�E�����@@�@@�@@�@@�@@:�ݓ������P�ʃe�[�u��.�ݓ����敪<BR>
     * �@@�@@�@@�@@�E���������@@�@@:�ݓ������P�ʃe�[�u��.�󒍓���<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~��<BR>
     *         �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * �@@�@@�������E�������w�肳�ꂽ�ꍇ�A�����^�~��<BR>
     *         �w��̌��" nulls first"��t������B<BR>
     * �@@�@@�������E�~�����w�肳�ꂽ�ꍇ�A�����^�~���w��<BR>
     *         �̌��" nulls last"��t������B<BR>
     * �@@�@@�@@����`��A"���t"���ŏ��l�������Ă���悤�ȃ\�[�g��<BR>
     *           �s����ׂ������A<BR>
     * �@@�@@�@@�@@�ݓ������P�ʃe�[�u��.�ݓ����敪��"���t"�Ƃ���<BR>
     *          �̒�`�́A�R�[�h�ł͂Ȃ�<BR>
     * �@@�@@�@@�@@null�ł���ׁA��L�p�����[�^���K�v�ƂȂ�B<BR>
     * <BR>
     * �Q)�@@�쐬�����\�[�g�����������Ԃ��B<BR>
     * @@param l_sortKey - �ݓ��\�[�g�L�[�̔z��<BR>
     * @@return String
     * @@roseuid 408659760394
     */
    //For Test Change private to public
//    private String createSortCondType(WEB3RuitoSortKey[] l_sortKey)
    public String createSortCondType(WEB3RuitoSortKey[] l_sortKey)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
                  "createSortCondType(WEB3RuitoSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);          
        if (l_sortKey == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        log.entering(STR_METHOD_NAME);
        //�Ԋ҂̒l�̐ݒ�
        String l_strReturn = "";
        for (int i = 0; i < l_sortKey.length; i++)
        {
            
            if(l_sortKey[i].keyItem.equals("ruitoProductCode") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "product_id";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc ";
            }
            
            if(l_sortKey[i].keyItem.equals("ruitoProductCode") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "product_id";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc ";
            }
            
            
            if(l_sortKey[i].keyItem.equals("ruitoDealingType") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "gp_sell_div";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc nulls first ";
            }
            
            if(l_sortKey[i].keyItem.equals("ruitoDealingType") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "gp_sell_div";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc nulls last ";
            }
            
            if(l_sortKey[i].keyItem.equals("orderDate") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "received_date_time";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc ";
            }
            
            if(l_sortKey[i].keyItem.equals("orderDate") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "received_date_time";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc ";
            }

            if (i >= 0 && i < l_sortKey.length - 1)
            {
                l_strReturn = l_strReturn + ", ";
            }
        }
        log.debug("order by l_strReturn = " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (validate�����)<BR>
     * �ݓ������P�ʂ��A���̒���������\�Ȓ������ǂ����𔻒肵�A<BR>
     * ����\�ȏꍇ"true"���A����s�̏ꍇ��"false"��ԋp����B<BR>
     * <BR>
     * �P)�ȉ��̂悤��ThreadLocalSystemAttributesRegistry��<BR>
     * �@@   ����J�����_�R���e�L�X�g�̓��e��ύX���A<BR>
     *      ������ԊǗ�.setTimestamp( )���R�[������B<BR>
     * <BR>
     * �@@������:�ݓ������P��.�ݓ��^�C�v��<BR>
     *      RuitoTypeEnum.�������t�@@���h�̏ꍇ<BR>
     * �@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 =<BR>
     *            WEB3TradingTimeTypeDef.����F<BR>
     * <BR>
     * �@@������:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A<BR>
     * �@@�@@����:�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_BUY��<BR>
     *     ���A����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *                 WEB3TradingTimeTypeDef.MMF(�ݒ�)<BR>
     * <BR>
     * �@@������:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A<BR>
     * �@@�@@����:�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_SELL<BR>
     *      �̎��A����J�����_�R���e�L�X�g.��t���ԋ敪 =<BR>
     *              WEB3TradingTimeTypeDef.MMF(�ݒ���)<BR>
     * <BR>
     * �@@�P�|�P)�@@�ݓ������R���ʃ`�F�b�N.get�C���X�^���X( )��<BR>
     *               �R�[������B<BR>
     * �@@�P�|�Q)�@@�ݓ������R���ʃ`�F�b�N.validate����\( )��<BR>
     *              �R�[������B<BR>
     * �@@�@@�@@    �@@[validate����\�̈���]<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:�ݓ������P��<BR>
     *  ��O�����������ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q)�@@�ݓ����敪�`�F�b�N<BR>
     * �@@�Q�|�P)�@@�߂�l��"true"�ł���A������:<BR>
     *      �ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.�������t�@@���h�ŁA<BR>
     * �@@�@@������:<BR>
     *      �ݓ������P��.������ʂ�OrderTypeEnum.RUITO_BUY�̏ꍇ<BR>
     * �@@�@@�Q�|�P�|�P)�@@�߂�l�Ɉ���:is����F���t��t�\���Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�Q)�@@�߂�l��"true"�ł���A<BR>
     * �@@�@@�@@�@@������:<BR>
     *        �ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.�������t�@@���h�ŁA<BR>
     * �@@�@@�@@�@@������:<BR>
     *        �ݓ������P��.������ʂ�OrderTypeEnum.RUITO_SELL�̏ꍇ<BR>
     * �@@�@@�Q�|�Q�|�P)�@@�߂�l�Ɉ���:is����F����t�\���Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�R)�@@�߂�l��"true"�ł���A<BR>
     * �@@�@@�@@�@@������:<BR>
     *          �ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A<BR>
     * �@@�@@�@@�@@������:<BR>
     *          �ݓ������P��.������ʂ�OrderTypeEnum.RUITO_BUY�̏ꍇ<BR>
     * �@@�@@�Q�|�R�|�P)�@@�߂�l�Ɉ���:isMMF���t��t�\���Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�S)�@@�߂�l��"true"�ł���A<BR>
     * �@@�@@�@@�@@������:<BR>
     *        �ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A<BR>
     * �@@�@@�@@�@@������:<BR>
     *        �ݓ������P��.������ʂ�OrderTypeEnum.RUITO_SELL�̏ꍇ<BR>
     * �@@�@@�Q�|�S�|�P)�@@�߂�l�Ɉ���:isMMF����t�\���Z�b�g����B<BR>
     * <BR>
     * �R)�@@�߂�l�����^�[������B<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P�ʃI�u�W�F�N�g<BR>
     * @@param isMidTermNationalDebtFoundBuyAcceptPossible - 
     * <BR>(is����F���t��t�\)<BR>
     * true:��t�^false:��t�s��<BR>
     * @@param isMidTermNationalDebtFoundSellAcceptPossible - 
     * <BR>(is����F����t�\)<BR>
     * true:��t�^false:��t�s��<BR>
     * 
     * @@param isMMFBuyAcceptPossible - isMMF���t��t�\<BR>
     * true:��t�^false:��t�s��<BR>
     * 
     * @@param isMMSellAcceptPossible - (isMM����t�\)<BR>
     * true:��t�^false:��t�s��<BR>
     * @@return boolean
     * @@roseuid 408659BF023C
     */
    // For Test Change private to public
    //private boolean validateCancelAbleUnable(
    public boolean validateCancelAbleUnable(
        RuitoOrderUnit l_ruitoOrderUnit,
        boolean isMidTermNationalDebtFoundBuyAcceptPossible,
        boolean isMidTermNationalDebtFoundSellAcceptPossible,
        boolean isMMFBuyAcceptPossible,
        boolean isMMSellAcceptPossible) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCancelAbleUnable()";
        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) 
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        int  l_RuitoType;
        l_RuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();    
        log.debug("l_RuitoType = "+new Integer(l_RuitoType).toString());
        //����:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.�������t�@@���h�̏ꍇ
        if (RuitoTypeEnum.CHUUKOKU_FUND.intValue() == l_RuitoType)
        {
            log.debug(
              "����:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.�������t�@@���h�̏ꍇ");
            l_context.setTradingTimeType(
               WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);              
        }
        
        //����:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A
        //����:�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_BUY�̎�
        if (RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(l_ruitoOrderUnit.getOrderType()))
        {
            log.debug("����:�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_BUY�̎�");
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);
        }
        
        //����:�ݓ������P��.�ݓ��^�C�v��RuitoTypeEnum.MMF�ł���A
        //����:�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_SELL�̎��A
        if (RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnit.getOrderType()))
        {
            log.debug("�ݓ������P��.������ʂ�OrderTypeEnum.RUITO_SELL�̎�");
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
        }      
       
        boolean l_blnValidateCheck = true;
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                 l_context); 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        try
        {   
  
            //�P�|�P)�@@�ݓ������R���ʃ`�F�b�N.get�C���X�^���X( )���R�[������B
            WEB3RuitoOrderManagerReusableValidationsCheck l_validationCheck =
                (WEB3RuitoOrderManagerReusableValidationsCheck) 
                WEB3RuitoOrderManagerReusableValidationsCheck
                .getInstance();

            //�P�|�Q)�@@�ݓ������R���ʃ`�F�b�N.validate����\( )���R�[������
            l_validationCheck.validateCancelPossible(l_ruitoOrderUnit);
            

        }        
        catch(OrderValidationException l_ex)
        {
            l_blnValidateCheck = false;
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnValidateCheck = false;
        }
        

        boolean l_blnReturn = false;
        //�Q)�@@�ݓ����敪�`�F�b�N
        
        if (l_blnValidateCheck && RuitoTypeEnum.CHUUKOKU_FUND.intValue() 
            == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //�߂�l�Ɉ���:is����F���t��t�\���Z�b�g����B
            log.debug("�߂�l�Ɉ���:is����F���t��t�\���Z�b�g����B");
            l_blnReturn = isMidTermNationalDebtFoundBuyAcceptPossible;
        }
        //�Q�|�Q)
        if (l_blnValidateCheck && RuitoTypeEnum.CHUUKOKU_FUND.intValue() 
            == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //�߂�l�Ɉ���:is����F����t�\���Z�b�g����B
            log.debug("�߂�l�Ɉ���:is����F����t�\���Z�b�g����B");
            l_blnReturn = isMidTermNationalDebtFoundSellAcceptPossible;
        }
        //�Q�|�R)
        if (l_blnValidateCheck && RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //�߂�l�Ɉ���:isMMF���t��t�\���Z�b�g����B
            log.debug("�߂�l�Ɉ���:isMMF���t��t�\���Z�b�g����");
			l_blnReturn = isMMFBuyAcceptPossible;
        }
        //�Q�|�S)
        if (l_blnValidateCheck && RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //�߂�l�Ɉ���:isMMF����t�\���Z�b�g����B
			log.debug("�߂�l�Ɉ���:isMMF����t�\���Z�b�g����");
			l_blnReturn = isMMSellAcceptPossible;
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * �w�肳�ꂽ�ݓ������P�ʂ̒�����ԋ敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̒�����ԋ敪:<BR>
     *  1:��t��(�V�K����)�@@      3:������(�V�K����)�@@ <BR>
     * 6:�������s(�V�K����)     12:��t��(�������)�@@<BR>
     * 14:������(�������)�@@�@@  15:�������s(�������)<BR>
     * 30:MRF����G���[�@@�@@       31:��t��(MRF���L��)<BR>
     * 32:������(MRF���L��)<BR>
     * <BR>
     * �P)�@@������Ԃ̔���<BR>
     * �@@�@@�@@�@@����:�������.MRF�������ʃR�[�h!=null�̏ꍇ<BR>
     * <BR>
     * �@@�P�|�P)�@@�ݓ������P�ʃe�[�u�����������A<BR>
     *              MRF���Ƃ��Ă̗ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�@@�@@�ݓ������P��.���ʃR�[�h = <BR>
     *                  ����:�������.MRF�������ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�ݓ������P��.�ݓ��^�C�v = RuitoTypeEnum.MRF<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P)�@@�������ʂ��P���ł͂Ȃ��ꍇ�A<BR>
     *                      �f�[�^�s�����Ƃ��ė�O���X���[����B<BR>
     *                      class    : WEB3BusinessLayerException<BR>
     *                      tag      : BUSINESS_ERROR_00201<BR>
     * <BR>
     * �@@�P�|�Q)�@@����:�������.�������=<BR>
     *               OrderStatusEnum.�������s�i��������j(15)�ŁA����<BR>
     * �@@�@@�@@�@@    MRF���.�������=<BR>
     *               OrderStatusEnum.�������s�i��������j(15)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@32�D�������s�i�l�q�e����������j�����^�[������B<BR>
     * <BR>
     * �@@�P�|�R)�@@����:�������.�������=<BR>
     *               OrderStatusEnum.��t��(�V�K����)�ŁA����<BR>
     * �@@�@@�@@�@@    MRF���.�������=<BR>
     *              OrderStatusEnum.��t��(�V�K����)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@30�D��t�ρiMRF��񂠂�j�����^�[������B<BR>
     * <BR>
     * �@@�P�|�S)�@@����:�������.�������=<BR>
     *              OrderStatusEnum.��t��(�V�K����)�ŁA����<BR>
     * �@@�@@�@@    �@@MRF���.�������=<BR>
     *              OrderStatusEnum.������(�V�K����)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@31�D�����ρiMRF��񂠂�j�����^�[������B<BR>
     * <BR>
     * �@@�P�|�T)�@@����:�������=OrderStatusEnum.������(�V�K����)�ŁA<BR>
     *              ����MRF���.�������=<BR>
     *              OrderStatusEnum.������(�V�K����)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@31�D�����ρiMRF��񂠂�j�����^�[������B<BR>
     * <BR>
     * �@@�P�|�U)�@@��L�ɍ��v���Ȃ��ꍇ�A<BR>
     *               ����:�������.������Ԃ����^�[������B<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P�ʃI�u�W�F�N�g
     * @@return String
     * @@roseuid 40865A7D0087
     */
    // For Test Change private to public
    //private String getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit) throws WEB3BaseException
    public String getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit)";
        if (l_ruitoOrderUnit == null)
        { 
            log.debug("����=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        String l_strMrfOrderRequestNumber = null;
        l_strMrfOrderRequestNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();
        
		// SONAR�Ŏ�t���������̏ꍇ�A            
		if (WEB3OrderRootDivDef.HOST.equals(l_ruitoOrderUnitRow.getOrderRootDiv()))
		{
			// ����:�������.�������=
			// OrderStatusEnum.�����ρi��������j(14)�̏ꍇ�A
			// 14�F�����ρi��������j�����^�[������B
			if(OrderStatusEnum.CANCELLED.equals(
					l_ruitoOrderUnit.getOrderStatus()))
			{
				return WEB3OrderStatusDivDef.ORDER_CANCELED;
			}
			// ����ȊO�̏ꍇ�A56�F����s�����^�[������B
			else
			{
				return WEB3OrderStatusDivDef.CANCEL_DISABLE;
			}
		}
		
        //�������.MRF�������ʃR�[�h!=null�̏ꍇ    
        if (l_strMrfOrderRequestNumber != null)
        {
            log.debug("�������.MRF�������ʃR�[�h!=null�̏ꍇ");
            try
            {
                String l_strWhere = "order_request_number = ? and ruito_type = ?";

                QueryProcessor l_qp = Processors.getDefaultProcessor();
                Object l_bindVars[] = { l_strMrfOrderRequestNumber, 
                                        RuitoTypeEnum.MRF };
                List l_lisRows =
                    l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE, l_strWhere, l_bindVars);

                if (l_lisRows != null && l_lisRows.size() != 1)
                {
                    log.debug("�f�[�^�s�����Ƃ���");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00201,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                RuitoOrderUnitParams l_RuitoProductParams = 
                            (RuitoOrderUnitParams)l_lisRows.get(0);
                
                //�P�|�Q)�@@����:�������.�������=
                //        OrderStatusEnum.�������s�i��������j(15)�ŁA����
                //�@@�@@�@@    MRF���.�������=<BR>
                //        OrderStatusEnum.�������s�i��������j(15)�̏ꍇ
                //        32�D�������s�i�l�q�e����������j�����^�[������B
                if (OrderStatusEnum.NOT_CANCELLED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.NOT_CANCELLED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.CANCEL_MRF_SELL_FAILED;
                }
                // �P�|�R)�@@����:�������.�������=
                //        OrderStatusEnum.��t��(�V�K����)�ŁA����
                //  �@@�@@    MRF���.�������=
                //         OrderStatusEnum.��t��(�V�K����)�̏ꍇ
                //         30�D��t�ρiMRF��񂠂�j�����^�[������B
                if (OrderStatusEnum.ACCEPTED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ACCEPTED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDERING_MRF_SELL;
                }
                //�P�|�S)�@@����:�������.�������=
                //        OrderStatusEnum.��t��(�V�K����)�ŁA����
                //      �@@MRF���.�������=
                //        OrderStatusEnum.������(�V�K����)�̏ꍇ
                //  �@@�@@�@@�@@31�D�����ρiMRF��񂠂�j�����^�[������B
                if (OrderStatusEnum.ACCEPTED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ORDERED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDER_MRF_SELL;
                }
                //�P�|�T)�@@����:�������=OrderStatusEnum.������(�V�K����)�ŁA
                //        ����MRF���.�������=
                //        OrderStatusEnum.������(�V�K����)�̏ꍇ
                //   �@@�@@�@@31�D�����ρiMRF��񂠂�j�����^�[������B
                if (OrderStatusEnum.ORDERED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ORDERED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDER_MRF_SELL;
                }


            }
            catch (DataNetworkException l_ex)
            {
                log.debug("__DataNetworkException__");
                log.error(
                    "__an unexpected error__",
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex));
            }
            catch (DataQueryException l_ex)
            {
                log.debug("__DataQueryException__");
                log.error(
                    "__an unexpected error__",
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex));
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_ruitoOrderUnit.getOrderStatus().intValue() + "";
    }

}@
