head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrderdMarketCodeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �D�揇�s��R�[�h���X�g�萔��`(WEB3TPOrderdMarketCodeListDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/21 asano(SCS) �V�K�쐬
*/
package webbroker3.tradingpower.define;

import webbroker3.common.define.WEB3MarketCodeDef;

/**
 * �D�揇�s��R�[�h���X�g�萔��`�C���^�[�t�F�C�X
 */
public interface WEB3TPOrderdMarketCodeListDef 
{
    
    /**
     * �D�揇�s��R�[�h���X�g
     */
    public static String[] ORDERD_MARKET_CODE_LIST
        = { WEB3MarketCodeDef.TOKYO,//����
            WEB3MarketCodeDef.OSAKA,//���
            WEB3MarketCodeDef.NAGOYA,//���É�
            WEB3MarketCodeDef.JASDAQ,//JASDAQ
            WEB3MarketCodeDef.NNM,//NNM
            WEB3MarketCodeDef.FUKUOKA,//����
            WEB3MarketCodeDef.SAPPORO//�D�y
          };

}
@
