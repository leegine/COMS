head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g(WEB3AdminForcedSettleReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬  �d�l�ύX���f��No.128
Revesion History : 2008/01/17 �И��� (���u) �d�l�ύX���f��No.181 183 185 188
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReferenceRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleReferenceRequest.class);

    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_reference";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (���X�R�[�h�ꗗ)<BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCodeList;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (���F���)<BR>
     * ���F���<BR>
     * <BR>
     * 0�F�@@�����F<BR>
     * 1�F�@@���F��<BR>
     * 2�F�@@�񏳔F<BR>
     * 9�F�@@�G���[<BR>
     */
    public String approveState;

    /**
     * (���F�҃R�[�h)<BR>
     * ���F�҃R�[�h<BR>
     */
    public String checker;

    /**
     * (�쐬����From)<BR>
     * �쐬����From<BR>
     */
    public String createDateFrom;

    /**
     * (�쐬����To)<BR>
     * �쐬����To<BR>
     */
    public String createDateTo;

    /**
     * (���F����From)<BR>
     * ���F����From<BR>
     */
    public String approveDateFrom;

    /**
     * (���F����To)<BR>
     * ���F����To<BR>
     */
    public String approveDateTo;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 4�F  �ۏ؋��ێ������i�I�����C���J�n�O�E�@@��j<BR>
     * 9�F�@@�蓮��������<BR>
     * 90�F�@@�Ǐ�(���)��������<BR>
     * 91�F�@@�Ǐ�(���)��������<BR>
     * <BR>
     * ���Ǐ�(���)��������<BR>
     * �@@�E�E�E �ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j or<BR>
     * �@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j�̂��ƁB<BR>
     * �Ǐ�(���)��������<BR>
     * �@@�E�E�E �ۏ؋��ێ������i��ԁj or<BR>
     * �@@�ۏ؋��ێ������i�I�����C���J�n�O�E�@@��j�̂��ƁB<BR>
     */
    public String forcedSettleReason;

    /**
     * (���ϊ���)<BR>
     * ���ϊ���<BR>
     */
    public Date closeDate;

    /**
     * (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * <BR>
     * 0005�F�@@�����c���s���G���[<BR>
     * 0006�F�@@������~�����G���[<BR>
     * 0016�F�@@���ϊ��������σG���[<BR>
     * 0017�F�@@�����E���n�����o�^�σG���[<BR>
     * 9001�F�@@���̑��G���[<BR>
     */
    public String errorReason;

    /**
     * (���F�敪)<BR>
     * ���F�敪<BR>
     * <BR>
     * 0�F�@@���F<BR>
     * 1�F�@@�񏳔F<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X����<BR>
     * �@@�R�[�����ꂽ�ꍇ�̂݃Z�b�g�����B<BR>
     */
    public String approveType;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X����<BR>
     * �@@�R�[�����ꂽ�ꍇ�̂݃Z�b�g�����B<BR>
     */
    public String taxType;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X����<BR>
     * �@@�R�[�����ꂽ�ꍇ�̂݃Z�b�g�����B<BR>
     */
    public String marketCode;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * ���_�E�����[�h�@@�\����R�[�����ꂽ�ꍇ�́A<BR>
�@@   * �@@�_�E�����[�h�y�[�W�ԍ��ƂȂ�B<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     * <BR>
     * ���_�E�����[�h�@@�\����R�[�����ꂽ�ꍇ�́A<BR>
     * �@@�_�E�����[�h�����ƂȂ�B<BR>
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3AdminForcedSettleSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 462CA4250276
     */
    public WEB3AdminForcedSettleReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01429       <BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B<BR>
     * �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E���X�R�[�h != ����<BR>
     * �@@�@@�@@�@@�E���X�R�[�h.length != 3<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00779       <BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�ڋq�R�[�h != ����<BR>
     * �@@�@@�@@�E�ڋq�R�[�h.length != 6<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00780       <BR>
     * <BR>
     * �R�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�����R�[�h != ����<BR>
     * �@@�@@�@@�E�����R�[�h.length != 5<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01067       <BR>
     * <BR>
     * �S�j�@@���F��ԃ`�F�b�N<BR>
     * �@@this.���F��� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�j�@@this.���F��Ԃɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u���F��Ԃ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"�����F"<BR>
     * �@@�@@�@@�E"���F��"<BR>
     * �@@�@@�@@�E"�񏳔F"<BR>
     * �@@�@@�@@�E"�G���["<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02753<BR>
     * <BR>
     * �T�j�@@�쐬����From/To�������`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.�쐬����From != null�̏ꍇ�A<BR>
     * �@@�@@this.�쐬����From����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[�i�쐬����From�j�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02754<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�쐬����To != null�̏ꍇ�A<BR>
     * �@@�@@this.�쐬����To����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[�i�쐬����To�j�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02755<BR>
     * <BR>
     * �@@�T�|�R�j�@@this.�쐬����From != null ���� �쐬����To != null�̏ꍇ�A<BR>
     * �@@�@@this.�쐬����From > this.�쐬����To�ł���΁A<BR>
     * �@@�@@�u�쐬����From/To�������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02715<BR>
     * <BR>
     * �U�j�@@���F����From/To�������`�F�b�N<BR>
     * �@@�U�|�P�j�@@this.���F����From != null�̏ꍇ�A<BR>
     * �@@�@@this.���F����From����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[�i���F����From�j�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02756<BR>
     * <BR>
     * �@@�U�|�Q�j�@@this.���F����To != null�̏ꍇ�A<BR>
     * �@@�@@this.���F����To����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[�i���F����To�j�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02757<BR>
     * <BR>
     * �@@�U�|�R�j�@@this.���F����From != null ���� ���F����To != null�̏ꍇ�A<BR>
     * �@@�@@this.���F����From > this.���F����To�ł���΁A<BR>
     * �@@�@@�u���F����From/To�������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02715<BR>
     * <BR>
     * �V�j�@@�������ϗ��R�`�F�b�N<BR>
     * �@@this.�������ϗ��R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�V�|�P�j�@@this.�������ϗ��R�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�������ϗ��R������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���ϊ�������"<BR>
     * �@@�@@�@@�E"�ۏ؋��ێ������i�y�x�j"<BR>
     * �@@�@@�@@�E"�ۏ؋��ێ������i�d�x�j"<BR>
     * �@@�@@�@@�E"�ۏ؋��ێ������i��ԁj"<BR>
     * �@@�@@�@@�E"�ۏ؋��ێ������i�@@��j"<BR>
     * �@@�@@�@@�E"�蓮��������"<BR>
     * �@@�@@�@@�E"�Ǐ�(���)��������"<BR>
     * �@@�@@�@@�E"�Ǐ�(���)��������"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02758<BR>
     * <BR>
     * �W�j�@@�����G���[���R�R�[�h�`�F�b�N<BR>
     * �@@this.�����G���[���R�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�W�|�P�j�@@this.�����G���[���R�R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�����G���[���R�R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"�����c���s���G���["<BR>
     * �@@�@@�@@�E"������~�����G���["<BR>
     * �@@�@@�@@�E"���ϊ��������σG���["<BR>
     * �@@�@@�@@�E"�����E���n�����o�^�σG���["<BR>
     * �@@�@@�@@�E"���̑��G���["<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02759<BR>
     * <BR>
     * �X�j�@@���F�敪�`�F�b�N<BR>
     * �@@this.���F�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�X�|�P�j�@@this.���F�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u���F�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���F"<BR>
     * �@@�@@�@@�E"�񏳔F"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02760<BR>
     * <BR>
     * �P�O�j�@@�����敪�`�F�b�N<BR>
     * �@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�O�|�P�j�@@this.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01303<BR>
     * <BR>
     * �P�P�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�P�|�P�j�@@this.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�E"���É�"<BR>
     * �@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�E"�D�y"<BR>
     * �@@�@@�@@�E"NNM"<BR>
     * �@@�@@�@@�E"JASDAQ"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00608<BR>
     * <BR>
     * �P�Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�Q�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�Q�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�P�Q�|�Q�|�P�j�@@�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �P�R�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�P�R�|�P�j�@@this.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�P�R�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�P�R�|�R�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �P�S�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�P�S�|�P�j�@@this.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�P�S�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�P�S�|�R�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 460200BE005C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        // �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
        // �P�j�@@���X�R�[�h�`�F�b�N
        // �@@�P�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A
        // �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B
        if (this.branchCodeList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�ꗗ�����w��ł��B");
        }

        //   �P�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B
        // �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�@@�E���X�R�[�h != ����
        // �@@�@@�@@�@@�E���X�R�[�h.length != 3
        int l_intBranchCodeLength = 0;
        l_intBranchCodeLength = this.branchCodeList.length;
        for (int i = 0;  i < l_intBranchCodeLength; i++)
        {
            final int l_intThree = 3;
            if ((!WEB3StringTypeUtility.isDigit(branchCodeList[i]))
                || (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != l_intThree))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }

        // �Q�j�@@�ڋq�R�[�h�`�F�b�N
        // �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�Q�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�E�ڋq�R�[�h != ����
        // �@@�@@�@@�E�ڋq�R�[�h.length != 6
        if (this.accountCode != null)
        {
            final int l_intSix = 6;
            if ((!WEB3StringTypeUtility.isDigit(this.accountCode))
                || (WEB3StringTypeUtility.getByteLength(this.accountCode) != l_intSix))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
        }

        // �R�j�@@�����R�[�h�`�F�b�N
        // �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�R�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�E�����R�[�h != ����
        // �@@�@@�@@�E�����R�[�h.length != 5
        if (this.productCode != null)
        {
            final int l_intFive = 5;
            if ((!WEB3StringTypeUtility.isDigit(this.productCode))
                || (WEB3StringTypeUtility.getByteLength(this.productCode) != l_intFive))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h�̓��͂��s���ł��B");
            }
        }

        // �S�j�@@���F��ԃ`�F�b�N
        // �@@this.���F��� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�S�|�P�j�@@this.���F��Ԃɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u���F��Ԃ�����`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"�����F"
        // �@@�@@�@@�E"���F��"
        // �@@�@@�@@�E"�񏳔F"
        // �@@�@@�@@�E"�G���["
        if (this.approveState != null)
        {
            if ((!WEB3ApproveStatusType.UNAPPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.APPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.NON_APPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.ERROR.equals(this.approveState)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02753,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���F��Ԃ�����`�̒l�B");
            }
        }

        //�T�j�@@�쐬����From/To�������`�F�b�N
        // �@@�T�|�P�j�@@this.�쐬����From != null�̏ꍇ�A
        // �@@�@@this.�쐬����From����t�^�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@�@@�@@�@@�u���͓��t�G���[�i�쐬����From�j�v�̗�O���X���[����B
        if (this.createDateFrom != null)
        {
            Date l_datCreateDateFrom = WEB3DateUtility.getDate(this.createDateFrom, "yyyyMMddHHmm");

            if (l_datCreateDateFrom == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02754,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͓��t�G���[�i�쐬����From�j�B");
            }
        }

        // �@@�T�|�Q�j�@@this.�쐬����To != null�̏ꍇ�A
        // �@@�@@this.�쐬����To����t�^�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@�@@�@@�@@�u���͓��t�G���[�i�쐬����To�j�v�̗�O���X���[����B
        if (this.createDateTo != null)
        {
            Date l_datCreateDateTo = WEB3DateUtility.getDate(this.createDateTo, "yyyyMMddHHmm");

            if (l_datCreateDateTo == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02755,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͓��t�G���[�i�쐬����To�j�B");
            }
        }

        // �@@�T�|�R�j�@@this.�쐬����From != null ���� �쐬����To != null�̏ꍇ�A
        // �@@�@@this.�쐬����From > this.�쐬����To�ł���΁A
        // �@@�@@�u�쐬����From/To�������G���[�v�̗�O���X���[����B
        if ((this.createDateFrom != null) && (this.createDateTo != null))
        {
            Date l_datCreateDateFrom = WEB3DateUtility.getDate(this.createDateFrom, "yyyyMMddHHmm");
            Date l_datCreateDateTo = WEB3DateUtility.getDate(this.createDateTo, "yyyyMMddHHmm");

            int l_intResultCompare = WEB3DateUtility.compare(l_datCreateDateFrom, l_datCreateDateTo);
            if (l_intResultCompare > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ώۊ��ԃG���[�B");
            }
        }

        // �U�j�@@���F����From/To�������`�F�b�N
        // �@@�U�|�P�j�@@this.���F����From != null�̏ꍇ�A
        // �@@�@@this.���F����From����t�^�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@�@@�@@�@@�u���͓��t�G���[�i���F����From�j�v�̗�O���X���[����B
        if (this.approveDateFrom != null)
        {
            Date l_datApproveDateFrom = WEB3DateUtility.getDate(this.approveDateFrom, "yyyyMMddHHmm");

            if (l_datApproveDateFrom == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02756,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͓��t�G���[�i���F����From�j�B");
            }
        }

        // �@@�U�|�Q�j�@@this.���F����To != null�̏ꍇ�A
        // �@@�@@this.���F����To����t�^�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@�@@�@@�@@�u���͓��t�G���[�i���F����To�j�v�̗�O���X���[����B
        if (this.approveDateTo != null)
        {
            Date l_datApproveDateTo = WEB3DateUtility.getDate(this.approveDateTo, "yyyyMMddHHmm");

            if (l_datApproveDateTo == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02757,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͓��t�G���[�i���F����To�j�B");
            }
         }

        // �@@�U�|�R�j�@@this.���F����From != null ���� ���F����To != null�̏ꍇ�A
        // �@@�@@this.���F����From > this.���F����To�ł���΁A
        // �@@�@@�u���F����From/To�������G���[�v�̗�O���X���[����B
        if ((this.approveDateFrom != null) && (this.approveDateTo != null))
        {
            Date l_datApproveDateFrom = WEB3DateUtility.getDate(this.approveDateFrom, "yyyyMMddHHmm");
            Date l_datApproveDateTo = WEB3DateUtility.getDate(this.approveDateTo, "yyyyMMddHHmm");

            int l_intResultCompare = WEB3DateUtility.compare(l_datApproveDateFrom, l_datApproveDateTo);
            if (l_intResultCompare > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ώۊ��ԃG���[�B");
            }
        }

        // �V�j�@@�������ϗ��R�`�F�b�N
        // �@@this.�������ϗ��R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�V�|�P�j�@@this.�������ϗ��R�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u�������ϗ��R������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"���ϊ�������"
        // �@@�@@�@@�E"�ۏ؋��ێ������i�y�x�j"
        // �@@�@@�@@�E"�ۏ؋��ێ������i�d�x�j"
        // �@@�@@�@@�E"�ۏ؋��ێ������i��ԁj"
        // �@@�@@�@@�E"�ۏ؋��ێ������i�@@��j"
        // �@@�@@�@@�E"�蓮��������"
        //�@@�@@�@@ �E"�Ǐ�(���)��������"
        //�@@�@@�@@ �E"�Ǐ�(���)��������"
        if (this.forcedSettleReason != null)
        {
            if ((!WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(this.forcedSettleReason))
                && (!WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_FIRST_DATE_EXCESS.equals(
                    this.forcedSettleReason))
                && (!WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_SECOND_DATE_EXCESS.equals(
                    this.forcedSettleReason)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������ϗ��R������`�̒l�B");
            }
        }

        // �W�j�@@�����G���[���R�R�[�h�`�F�b�N
        // �@@this.�����G���[���R�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�W�|�P�j�@@this.�����G���[���R�R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u�����G���[���R�R�[�h������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"�����c���s���G���["
        // �@@�@@�@@�E"������~�����G���["
        // �@@�@@�@@�E"���ϊ��������σG���["
        // �@@�@@�@@�E"�����E���n�����o�^�σG���["
        // �@@�@@�@@�E"���̑��G���["
        if (this.errorReason != null)
        {
            if ((!WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.OTHRE_ERROR.equals(this.errorReason)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02759,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����G���[���R�R�[�h������`�̒l�B");
            }
        }

        // �X�j�@@���F�敪�`�F�b�N
        // �@@this.���F�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�X�|�P�j�@@this.���F�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u���F�敪������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"���F"
        // �@@�@@�@@�E"�񏳔F"
        if (this.approveType != null)
        {
            if ((!WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType))
                && (!WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���F�敪������`�̒l�B");
            }
        }

        // �P�O�j�@@�����敪�`�F�b�N
        // �@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B>
        // �@@�P�O�|�P�j�@@this.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"���"
        // �@@�@@�@@�E"����"
        if (this.taxType != null)
        {
            if ((!WEB3TaxTypeDef.NORMAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.SPECIAL.equals(this.taxType)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        // �P�P�j�@@�s��R�[�h�`�F�b�N
        // �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�P�P�|�P�j�@@this.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"����"
        // �@@�@@�@@�E"���"
        // �@@�@@�@@�E"���É�"
        // �@@�@@�@@�E"����"
        // �@@�@@�@@�E"�D�y"
        // �@@�@@�@@�E"NNM"
        // �@@�@@�@@�E"JASDAQ"
        if (this.marketCode != null)
        {
            if ((!WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        // �P�Q�j�@@�\�[�g�L�[�`�F�b�N
        // �@@�P�Q�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A
        // �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        // �@@�P�Q�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        // �@@�@@�P�Q�|�Q�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
        if (this.sortKeys != null)
        {
            int l_intSortKeysLength = this.sortKeys.length;
            for (int i = 0; i < l_intSortKeysLength; i++)
            {
                WEB3AdminForcedSettleSortKeyUnit l_adminForcedSettleSortKeyUnit = sortKeys[i];
                l_adminForcedSettleSortKeyUnit.validate();
            }
        }

        // �P�R�j�@@�v���y�[�W�ԍ��`�F�b�N
        // �@@�P�R�|�P�j�@@this.�v���y�[�W�ԍ� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        // �@@�P�R�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        // �@@�P�R�|�R�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        int l_intPageIndex = Integer.parseInt(this.pageIndex);
        if (l_intPageIndex <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �P�S�j�@@�y�[�W���\���s���`�F�b�N
        // �@@�P�S�|�P�j�@@this.�y�[�W���\���s�� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        // �@@�P�S�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �@@�P�S�|�R�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        int l_intPageSize = Integer.parseInt(pageSize);
        if (l_intPageSize <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleReferenceResponse(this);
    }
}
@
