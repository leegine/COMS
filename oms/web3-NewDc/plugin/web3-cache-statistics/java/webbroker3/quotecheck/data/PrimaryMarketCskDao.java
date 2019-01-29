head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PrimaryMarketCskDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quotecheck.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link PrimaryMarketCskDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PrimaryMarketCskRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PrimaryMarketCskPK 
 * @@see PrimaryMarketCskRow 
 */
public class PrimaryMarketCskDao extends DataAccessObject {


  /** 
   * ����{@@link PrimaryMarketCskDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PrimaryMarketCskRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PrimaryMarketCskRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PrimaryMarketCskDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PrimaryMarketCskDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PrimaryMarketCskRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PrimaryMarketCskRow )
                return new PrimaryMarketCskDao( (PrimaryMarketCskRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PrimaryMarketCskRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PrimaryMarketCskRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PrimaryMarketCskRow}�I�u�W�F�N�g 
    */
    protected PrimaryMarketCskDao( PrimaryMarketCskRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PrimaryMarketCskRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PrimaryMarketCskRow getPrimaryMarketCskRow() {
        return row;
    }


  /** 
   * �w���{@@link PrimaryMarketCskRow}�I�u�W�F�N�g����{@@link PrimaryMarketCskDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PrimaryMarketCskRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PrimaryMarketCskDao}�擾�̂��߂Ɏw���{@@link PrimaryMarketCskRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PrimaryMarketCskDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PrimaryMarketCskDao forRow( PrimaryMarketCskRow row ) throws java.lang.IllegalArgumentException {
        return (PrimaryMarketCskDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PrimaryMarketCskRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PrimaryMarketCskRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PrimaryMarketCskPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PrimaryMarketCskParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PrimaryMarketCskRow.TYPE );
    }


  /** 
   * {@@link PrimaryMarketCskRow}����ӂɓ��肷��{@@link PrimaryMarketCskPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PrimaryMarketCskRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PrimaryMarketCskParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PrimaryMarketCskPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PrimaryMarketCskPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_fundCode �����Ώۂł���p_fundCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PrimaryMarketCskRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PrimaryMarketCskRow findRowByPk( String p_fundCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskPK pk = new PrimaryMarketCskPK( p_fundCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���PrimaryMarketCskPK�I�u�W�F�N�g����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PrimaryMarketCskPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PrimaryMarketCskRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PrimaryMarketCskRow findRowByPk( PrimaryMarketCskPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PrimaryMarketCskRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(PrimaryMarketCskRow)}���g�p���Ă��������B 
   */
    public static PrimaryMarketCskDao findDaoByPk( String p_fundCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskPK pk = new PrimaryMarketCskPK( p_fundCode );
        PrimaryMarketCskRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PrimaryMarketCskPK)}�����{@@link #forRow(PrimaryMarketCskRow)}���g�p���Ă��������B 
   */
    public static PrimaryMarketCskDao findDaoByPk( PrimaryMarketCskPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_fundCode, and �ɂĎw��̒l�����ӂ�{@@link PrimaryMarketCskRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_fundCode �����Ώۂł���p_fundCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_fundCode, and �̒l�ƈ�v����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static PrimaryMarketCskRow findRowByFundCode( String p_fundCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PrimaryMarketCskRow.TYPE,
            "fund_code=?",
            null,
            new Object[] { p_fundCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PrimaryMarketCskRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PrimaryMarketCskDao.findRowsByFundCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByFundCode(String)}�����{@@link #forRow(PrimaryMarketCskRow)}���g�p���Ă��������B 
   */
    public static PrimaryMarketCskDao findDaoByFundCode( String p_fundCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFundCode( p_fundCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_fundCode, p_primaryMarketCode, and �ɂĎw��̒l�Ɉ�v����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_fundCode �����Ώۂł���p_fundCode�t�B�[���h�̒l
   * @@param p_primaryMarketCode �����Ώۂł���p_primaryMarketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_fundCode, p_primaryMarketCode, and �̒l�ƈ�v����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByFundCodePrimaryMarketCode( String p_fundCode, String p_primaryMarketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PrimaryMarketCskRow.TYPE,
            "fund_code=? and primary_market_code=?",
            null,
            new Object[] { p_fundCode, p_primaryMarketCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByFundCodePrimaryMarketCode(String, String)}�����{@@link #forRow(PrimaryMarketCskRow)}���g�p���Ă��������B 
   */
    public static List findDaosByFundCodePrimaryMarketCode( String p_fundCode, String p_primaryMarketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByFundCodePrimaryMarketCode( p_fundCode, p_primaryMarketCode ) );
    }

}
@
