head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��M�ݓ��������Ɖ�Unit(WEB3AdminORMutualRuitoOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/19 ���� (���u) �d�l�ύX�E���f��069
Revision History : 2007/01/12 ���� (���u) �d�l�ύX�E���f��089
Revesion History : 2007/02/26 �����(���u)�d�l�ύX���f��No.094
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

/**
 * (�Ǘ��ғ��M�ݓ��������Ɖ�Unit)<BR>
 * <BR>
 * �Ǘ��ғ��M�ݓ��������Ɖ�Unit�N���X<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@��� 1�F�@@���� 2�F�@@���̑�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.NORMAL 1: Def.SPECIAL<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (���ϋ敪)<BR>
     * <BR>
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�@@�~��<BR>
     * 2�F�@@�O��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * settleDiv<BR>
     * 1: Def.JAPANESE_CURRENCY<BR>
     * 2: Def.FOREIGN_CURRENCY<BR>
     * <BR>
     */
    public String settleDiv = null;

    /**
     * (����ID)<BR>
     * <BR>
     * ����ID<BR>
     * <BR>
     * productId<BR>
     * <BR>
     */
    public String productId;

    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (���敪)<BR>
     * <BR>
     * ���敪<BR>
     * <BR>
     * 2�F�@@�S���w��<BR>
     * 3�F�@@���z�w��<BR>
     * 4�F�@@�����w�� <BR>
     * �����t�̏ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * sellDiv<BR>
     * 2: Def.ALL_DESIGNATE<BR>
     * 3: Def.MONEY_DESIGNATE<BR>
     * 4: Def.COUNT_DESIGNATE <BR>
     * If Def.BUY, set null<BR>
     * <BR>
     */
    public String sellDiv = null;

    /**
     * (��n���@@)<BR>
     * <BR>
     * ��n���@@<BR>
     * <BR>
     * 1�F�@@��s�U��<BR>
     * 2�F�@@�،���������<BR>
     * 3�F�@@���֌W<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDiv<BR>
     * 1: Def.BANK_TRANSFER<BR>
     * 2: Def.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     * 3: Def.IRRELEVENT_BUY<BR>
     * <BR>
     */
    public String deliveryDiv;

    /**
     * (����)<BR>
     * <BR>
     * ����<BR>
     * <BR>
     * executionTimestamp<BR>
     * <BR>
     */
    public Date executionTimestamp;
    
    /**
     * (�Љ�敪)<BR>
     * �Љ�敪<BR> 
     * <BR>
     * null:�w�薳��<BR> 
     * 1:���ڎ��<BR> 
     * 2:�P���Љ�<BR> 
     * 3:���i�Љ�<BR> 
     * 4:������<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (�Љ�X�R�[�h)<BR>
     */
    public String introduceStoreCode;

    /**
     * (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * 0�F��� <BR>
     * 1�F����<BR>
     */
    public String sellBuyDiv;

    /**
     * (�������ʋ敪)<BR>
     * �������ʋ敪<BR>
     * <BR>
     * 0:����<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String mutualOrderQuantityType;

    /**
     * (�T�Z��n����ʉ݃R�[�h)<BR>
     * �T�Z��n����ʉ݃R�[�h<BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String estimatedPriceCurrencyCode;

    /**
     * (�O��MMF�t���O)<BR>
     * �O��MMF�t���O<BR>
     * <BR>
     * true:�������O��MMF<BR>
     * false:�������O��MMF�łȂ�<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * �i�Ǘ��ғ��M�ݓ��������Ɖ�Unit�j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * -----<English>-----------------<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefUnit<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * Unit
     * @@roseuid 41DB779F006D
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefUnit()
    {

    }
}
@
